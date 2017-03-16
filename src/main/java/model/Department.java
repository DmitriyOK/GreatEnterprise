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

    private int id;
    private String name;

    public Department() {
    }


    public int getId() {
        return id;
    }

    @Column(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Column(name = "departmentName")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
