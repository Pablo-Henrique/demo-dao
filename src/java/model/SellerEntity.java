package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("all")
public class SellerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5858441523904310814L;

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double baseSalary;

    private DepartmentEntity departmentEntity;

    public SellerEntity(Integer id, String name, String email, Date birthDate, Double baseSalary, DepartmentEntity departmentEntity) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.departmentEntity = departmentEntity;
    }

    public SellerEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public DepartmentEntity getDepartment() {
        return departmentEntity;
    }

    public void setDepartment(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerEntity sellerEntity = (SellerEntity) o;
        return Objects.equals(id, sellerEntity.id) && Objects.equals(name, sellerEntity.name) && Objects.equals(email, sellerEntity.email) && Objects.equals(birthDate, sellerEntity.birthDate) && Objects.equals(baseSalary, sellerEntity.baseSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthDate, baseSalary);
    }

    @Override
    public String toString() {
        return "Seller{" + "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", baseSalary=" + baseSalary +
                '}';
    }
}
