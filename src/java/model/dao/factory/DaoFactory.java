package model.dao.factory;

import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.DepartmentImpl;
import model.dao.impl.SellerDaoImpl;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoImpl();
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentImpl();
    }
}
