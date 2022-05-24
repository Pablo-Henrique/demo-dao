package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("all")
public final class DepartmentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2958436501723651053L;

    private Integer id;
    private String name;

    public DepartmentEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentEntity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb;
        sb = new StringBuffer("{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
