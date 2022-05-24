package model.dao;

import model.DepartmentEntity;

import java.util.List;

public interface DepartmentDao {

    void insert(DepartmentEntity departmentEntity);
    void update(DepartmentEntity departmentEntity);
    void deleteById(Integer id);
    DepartmentEntity findById(Integer id);
    List<DepartmentEntity> findAll();
}
