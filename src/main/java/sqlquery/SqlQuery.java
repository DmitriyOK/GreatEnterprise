package sqlquery;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public enum SqlQuery {
    QUERY_ALL_FIELDS("SELECT department.id AS 'departmentId', departmentName,"+
            "employer.id AS 'employerId', login, password, firstName, lastName, departmentId,"+
            "employerWorkDay.id AS 'employerWorkDayId', employerId, startTime, finishTime, unixStartTime, unixFinishTime FROM employerWorkDay " +
            "JOIN department ON employerWorkDay.departmentId = department.id "+
            "JOIN employer ON employerWorkDay.employerId = employer.id;"),

    ALL("SELECT * FROM ?"),
    FIND_BY_LOGIN("SELECT * FROM :tableName WHERE login= ?"),
    EMPLOYER_WORK_DAY_BY_PERIOD("SELECT * FROM employerworkday WHERE unixStartTime >=? and unixFinishTime <=? and employerId = ?"),
    CURRENT_DAY_REPORT("SELECT * FROM employerworkday WHERE unixStartTime >=? or unixStartTime is null ORDER BY unixStartTime DESC"),
    INSERT_NEW_EMPOYER_WORK_DAY("INSERT INTO employerworkday(employerId, startTime, unixStartTime) values(?, ?, ?)"),
    UPDATE_EMPLOYER_WORK_DAY("UPDATE employerworkday SET finishTime=?, unixFinishTime=? WHERE id = ?");

    private final String query;

     SqlQuery(final String query){
       this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
