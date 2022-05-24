package application;

import model.DepartmentEntity;
import model.SellerEntity;
import model.dao.SellerDao;
import model.dao.factory.DaoFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller FindById =====");
        SellerEntity seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n === TEST 2: Seller FindByDepartment =====");
        DepartmentEntity department = new DepartmentEntity(2, null);
        List<SellerEntity> sellers = sellerDao.findByDepartment(department);

        sellers.forEach(System.out::println);
    }
}
