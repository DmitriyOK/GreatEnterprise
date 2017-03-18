package model;

import annotation.Column;
import annotation.Entity;
import annotation.Table;

/**
 * Простой Java-oбъект для представления записи таблицы "employer"
 */

@Entity
@Table(name = "employer")
public class Employer {

    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

    public Employer() {
    }

    @Column(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "login")
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
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
                ", password='" + null + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
