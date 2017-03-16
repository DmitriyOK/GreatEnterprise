package model;

import annotation.*;

import java.util.Date;

/**
 * Created by Dmitriy on 15.03.2017.
 */

@Entity
@Table(name = "employerworkday")
public class EmployerWorkDay {

    private Integer id;
    private Integer employerId;
    private String startTime;
    private String finishTime;
    private int unixStartTime;
    private int unixFinishTime;
    private Employer employer;
    private Department department;


    public EmployerWorkDay() {
    }

    @Column(name ="id")
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "employerId")
    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    @Column(name = "startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    @Column(name="finishTime")
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Column(name = "unixStartTime")
    public void setUnixStartTime(int unixStartTime) {
        this.unixStartTime = unixStartTime;
    }

    @Column(name ="unixFinishTime")
    public void setUnixFinishTime(int unixFinishTime) {
        this.unixFinishTime = unixFinishTime;
    }

    @JoinTable(name = "employer", columnName = "id")
    @ForeignKey(colummName="employerId")
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @JoinTable(name="department", columnName = "id")
    @ForeignKey(colummName = "departmentId")
    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public int getUnixStartTime() {
        return unixStartTime;
    }

    public int getUnixFinishTime() {
        return unixFinishTime;
    }

    public Employer getEmployer() {
        return employer;
    }

    public Department getDepartment() {
        return department;
    }



    @Override
    public String toString() {
        return "EmployerWorkDay{" +
                "id=" + id +
                ", employerId=" + employerId +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", unixStartTime=" + unixStartTime +
                ", unixFinishTime=" + unixFinishTime +
                '}';
    }
}
