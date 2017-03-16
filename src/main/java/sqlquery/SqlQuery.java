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

   ALL("SELECT * FROM :tableName");

    private final String query;

     SqlQuery(final String query){
       this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
