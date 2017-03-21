package worktime.model;

import worktime.annotation.Column;
import worktime.annotation.Entity;
import worktime.annotation.Table;

/**
 * Простой Java-объект для представления записи таблицы "department"
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
