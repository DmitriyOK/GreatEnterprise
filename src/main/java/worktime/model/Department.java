package worktime.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Простой Java-объект для представления записи таблицы "department"
 */

@Entity
@Table(name ="department")
public class Department {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "departmentName")
    private String departmentName;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    Set<Employer> employers;

    public Department() {
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public String getDepartmentName() {
        return departmentName;
    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(Set<Employer> employers) {
        this.employers = employers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", employer=" + employers +
                '}';
    }
}
