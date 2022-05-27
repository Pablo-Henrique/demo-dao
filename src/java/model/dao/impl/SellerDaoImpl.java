package model.dao.impl;

import connections.DatabaseConnection;
import exceptions.DatabaseException;
import model.DepartmentEntity;
import model.SellerEntity;
import model.dao.SellerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public record SellerDaoImpl(Connection connection) implements SellerDao {

    @Override
    public void insert(SellerEntity sellerEntity) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId)" + "values (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, sellerEntity.getName());
            preparedStatement.setString(2, sellerEntity.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(sellerEntity.getBirthDate().getTime()));
            preparedStatement.setDouble(4, sellerEntity.getBaseSalary());
            preparedStatement.setInt(5, sellerEntity.getDepartment().getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    sellerEntity.setId(id);
                }
                DatabaseConnection.resultSetClose(resultSet);
            } else {
                throw new DatabaseException("Unexpected error! No rowns affected");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DatabaseConnection.statementClose(preparedStatement);
        }
    }

    @Override
    public void update(SellerEntity sellerEntity) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE seller set Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                    "WHERE Id = ?");

            preparedStatement.setString(1, sellerEntity.getName());
            preparedStatement.setString(2, sellerEntity.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(sellerEntity.getBirthDate().getTime()));
            preparedStatement.setDouble(4, sellerEntity.getBaseSalary());
            preparedStatement.setInt(5, sellerEntity.getDepartment().getId());
            preparedStatement.setInt(6, sellerEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DatabaseConnection.statementClose(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection().prepareStatement("DELETE FROM seller WHERE Id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }finally {
            DatabaseConnection.statementClose(preparedStatement);
        }

    }

    @Override
    public SellerEntity findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("select seller.*, department.Name as DepName from seller " +
                    "inner join department on seller.DepartmentId = department.Id where seller.Id = ?");

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                DepartmentEntity department = instantiateDepartment(resultSet);
                return instantiateSeller(resultSet, department);
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DatabaseConnection.statementClose(statement);
            DatabaseConnection.resultSetClose(resultSet);
        }
    }

    @Override
    public List<SellerEntity> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("" +
                    "select seller.*, department.Name as DepName " +
                    "from seller inner join department " +
                    "on seller.DepartmentId = department.Id " +
                    "order by Name");

            resultSet = statement.executeQuery();

            List<SellerEntity> sellers = new ArrayList<>();
            Map<Integer, DepartmentEntity> entityMap = new HashMap<>();

            while (resultSet.next()) {

                DepartmentEntity department = entityMap.get(resultSet.getInt("DepartmentId"));

                if (department == null) {
                    department = instantiateDepartment(resultSet);
                    entityMap.put(resultSet.getInt("DepartmentId"), department);
                }
                sellers.add(instantiateSeller(resultSet, department));
            }
            return sellers;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DatabaseConnection.statementClose(statement);
            DatabaseConnection.resultSetClose(resultSet);
        }
    }

    @Override
    public List<SellerEntity> findByDepartment(DepartmentEntity departmentEntity) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("" +
                    "select seller.*, department.Name as DepName " +
                    "from seller inner join department " +
                    "on seller.DepartmentId = department.Id " +
                    "where DepartmentId = ? " +
                    "order by Name");

            statement.setInt(1, departmentEntity.getId());
            resultSet = statement.executeQuery();

            List<SellerEntity> sellers = new ArrayList<>();
            Map<Integer, DepartmentEntity> entityMap = new HashMap<>();

            while (resultSet.next()) {

                DepartmentEntity department = entityMap.get(resultSet.getInt("DepartmentId"));

                if (department == null) {
                    department = instantiateDepartment(resultSet);
                    entityMap.put(resultSet.getInt("DepartmentId"), department);
                }
                sellers.add(instantiateSeller(resultSet, department));
            }
            return sellers;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DatabaseConnection.statementClose(statement);
            DatabaseConnection.resultSetClose(resultSet);
        }
    }

    private SellerEntity instantiateSeller(ResultSet resultSet, DepartmentEntity department) throws SQLException {
        SellerEntity seller = new SellerEntity();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setDepartment(department);
        return seller;
    }

    private DepartmentEntity instantiateDepartment(ResultSet resultSet) throws SQLException {
        DepartmentEntity department = new DepartmentEntity();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }
}
