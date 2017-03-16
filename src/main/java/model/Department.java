package model;

import annotation.Column;
import annotation.Entity;
import annotation.Table;

/**
 * Created by Dmitriy on 15.03.2017.
 */

@Entity
@Table(name ="department")
public class Department {

    private Integer id;
    private String departmentName;

    public Department() {
    }

    @Column(name = "id")
    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "departmentName")
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + departmentName + '\'' +
                '}';
    }
}
