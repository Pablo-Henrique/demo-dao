package model.dao.impl;

import connections.DatabaseConnection;
import exceptions.DatabaseException;
import model.DepartmentEntity;
import model.SellerEntity;
import model.dao.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class SellerDaoImpl implements SellerDao {

    private Connection connection;

    public SellerDaoImpl(Connection connection) {
        this.connection = connection;
    }

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
            statement = connection.prepareStatement(
                    """
                               select seller.*, department.Name as DepName
                               from seller inner join department
                               on seller.DepartmentId = department.Id
                               where seller.Id = ?
                            """);

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

    private SellerEntity instantiateSeller(ResultSet resultSet, DepartmentEntity department) throws SQLException{
        SellerEntity seller = new SellerEntity();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setDepartment(department);
        return seller;
    }

    private DepartmentEntity instantiateDepartment(ResultSet resultSet) throws SQLException{
        DepartmentEntity department = new DepartmentEntity();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }

    @Override
    public List<SellerEntity> findAll() {
        return null;
    }
}
