package worktime.model;

import worktime.annotation.*;

/**
 * Простой Java-объект для представления данных таблицы "employerworkday"
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
    private boolean isOnline;


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

    @Column(name ="isOnline")
    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
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

    @Override
    public String toString() {
        return "id=" + id +
                ", employerId=" + employerId +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", unixStartTime=" + unixStartTime +
                ", unixFinishTime=" + unixFinishTime +
                ", isOnline=" + isOnline ;
    }
}
