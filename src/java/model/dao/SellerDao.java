package model.dao;

import model.DepartmentEntity;
import model.SellerEntity;

import java.util.List;

public interface SellerDao {

    void insert(SellerEntity sellerEntity);

    void update(SellerEntity sellerEntity);

    void deleteById(Integer id);

    SellerEntity findById(Integer id);

    List<SellerEntity> findAll();

    List<SellerEntity> findByDepartment(DepartmentEntity department);
}
