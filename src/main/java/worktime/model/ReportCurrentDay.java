package worktime.model;

import worktime.annotation.Column;
import worktime.annotation.Entity;
import worktime.annotation.Table;

/**
 * Класс для результативной таблицы запроса {@link worktime.sqlquery.SqlQuery#CURRENT_DAY_REPORT}
 */
@Entity
@Table(name = "employerworkday")
public class ReportCurrentDay {

        private String startTime;
        private String finishTime;
        private int unixStartTime;
        private int unixFinishTime;
        private String login;
        private boolean isOnline;

    public ReportCurrentDay() {
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

        @Column(name ="login")
        public void setLogin(String login) {
            this.login = login;
        }

        @Column(name ="isOnline")
        public void setOnline(boolean online) {
            isOnline = online;
        }

        public boolean isOnline() {
            return isOnline;
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

        public String getLogin() {
            return login;
        }

    @Override
    public String toString() {
        return  "  login='" + login + '\'' +
                "  startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", unixStartTime=" + unixStartTime +
                ", unixFinishTime=" + unixFinishTime +
                ", isOnline=" + isOnline;
    }
}
