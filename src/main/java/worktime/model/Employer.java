package worktime.model;


import javax.persistence.*;

/**
 * Простой Java-oбъект для представления записи таблицы "employer"
 */

@Entity
@Table(name = "employer")
public class Employer {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "lastName")
    private String firstName;

    @Column(name = "firstName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name ="departmentId")
    private Department department;

    public Employer() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department.getDepartmentName() +
                '}';
    }
}
