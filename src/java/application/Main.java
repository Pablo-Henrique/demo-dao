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
        SellerEntity findById = sellerDao.findById(3);

        System.out.println(findById);

        System.out.println("\n === TEST 2: Seller FindByDepartment =====");
        DepartmentEntity department = new DepartmentEntity(2, null);
        List<SellerEntity> findByDepartment = sellerDao.findByDepartment(department);

        findByDepartment.forEach(System.out::println);

        System.out.println("\n === TEST 3: Seller FindAll =====");
        List<SellerEntity> findAll = sellerDao.findAll();
        findAll.forEach(System.out::println);

    }
}
