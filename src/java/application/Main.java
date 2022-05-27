package application;

import model.DepartmentEntity;
import model.SellerEntity;
import model.dao.SellerDao;
import model.dao.factory.DaoFactory;

import java.util.Date;
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

        System.out.println("\n === TEST 4: Seller Insert =====");
        SellerEntity seller = new SellerEntity(null, "Greg", "Greg@gmail.com", new Date(), 4000.00, department);
        sellerDao.insert(seller);
        System.out.println("Inserted! new id =  " + seller.getId());

        System.out.println("\n === TEST 5: Seller Update =====");
        SellerEntity sellerUpdate = sellerDao.findById(1);
        sellerUpdate.setName("Marta Waine");
        sellerUpdate.setEmail("Marta@gmail.com");
        sellerDao.update(sellerUpdate);

    }
}
