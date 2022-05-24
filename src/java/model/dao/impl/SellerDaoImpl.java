package model.dao.impl;

import annotations.Query;
import connections.DatabaseConnection;
import exceptions.DatabaseException;
import model.DepartmentEntity;
import model.SellerEntity;
import model.dao.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public record SellerDaoImpl(Connection connection) implements SellerDao {

    @Override
    public void insert(SellerEntity sellerEntity) {

    }

    @Override
    public void update(SellerEntity sellerEntity) {

    }

    @Override
    public void deleteById(Integer id) {

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
        return null;
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
