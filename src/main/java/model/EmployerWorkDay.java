package model;

import annotation.Column;
import annotation.Entity;
import annotation.ForeignKey;
import annotation.Table;

/**
 * Created by Dmitriy on 15.03.2017.
 */

@Entity
@Table(name = "employerworkday")
public class EmployerWorkDay {


    private int id;

    @ForeignKey(tableName = "employer")
    private int employerId;

    @ForeignKey(tableName = "department")
    private int departmentId;
    private String startTime;
    private String finishTime;
    private int unixStartTime;
    private int unixFinishTime;

    public EmployerWorkDay() {
    }

    public int getId() {
        return id;
    }

    @Column(name ="id")
    public void setId(int id) {
        this.id = id;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getStartTime() {
        return startTime;
    }

    @Column(name = "startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    @Column(name="finishTime")
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getUnixStartTime() {
        return unixStartTime;
    }
    @Column(name = "unixStartTime")
    public void setUnixStartTime(int unixStartTime) {
        this.unixStartTime = unixStartTime;
    }

    public int getUnixFinishTime() {
        return unixFinishTime;
    }

    @Column(name ="unixFinishTime")
    public void setUnixFinishTime(int unixFinishTime) {
        this.unixFinishTime = unixFinishTime;
    }

    @Override
    public String toString() {
        return "EmployerWorkTime{" +
                "id=" + id +
                ", employerId=" + employerId +
                ", departmentId=" + departmentId +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", unixStartTime=" + unixStartTime +
                ", unixFinishTime=" + unixFinishTime +
                '}';
    }
}
