package model.dao.factory;

import connections.DatabaseConnection;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.DepartmentImpl;
import model.dao.impl.SellerDaoImpl;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoImpl(DatabaseConnection.startConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentImpl();
    }
}
