package application;

import model.SellerEntity;
import model.dao.SellerDao;
import model.dao.factory.DaoFactory;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller FindById =====");
        SellerEntity seller = sellerDao.findById(3);

        System.out.println(seller);
    }
}
