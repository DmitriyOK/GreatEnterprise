package sqlquery;

/**
 * Класс перечислений используемых запросов.
 */
public enum SqlQuery {

    CURRENT_DAY_REPORT("SELECT  login, startTime, finishTime, unixStartTime, unixFinishTime, isOnLine "+
    "FROM employerworkday "+
    "JOIN employer ON employer.id = employerworkday.employerId "+
    "WHERE unixStartTime >=? "+
    "ORDER BY unixStartTime DESC"),

    SELECTED_DAY_REPORT("SELECT  login, startTime, finishTime, unixStartTime, unixFinishTime, isOnLine "+
            "FROM employerworkday "+
            "JOIN employer ON employer.id = employerworkday.employerId "+
            "WHERE (unixStartTime BETWEEN ? AND ?) "+
            "ORDER BY unixStartTime DESC"),

    FIND_BY_LOGIN("SELECT * FROM :tableName " +
            "WHERE login= ?"),

    EMPLOYER_WORK_DAY_BY_PERIOD("SELECT * " +
            "FROM employerworkday " +
            "WHERE unixStartTime >=? and (unixFinishTime <=? or unixFinishTime is null) and employerId = ? " +
            "ORDER BY unixStartTime DESC"),

    SET_STATUS(("UPDATE employerworkday " +
            "SET isOnline=? " +
            "WHERE id = ?")),

    CURRENT_EMPLOYER_WORK_DAY("SELECT * " +
            "FROM employerworkday " +
            "WHERE unixStartTime>= ? and employerId =?"),

    UPDATE_EMPLOYER_WORK_DAY_BEGIN("UPDATE employerworkday " +
            "SET startTime =?, unixStartTime=? " +
            "WHERE id = ?"),

    UPDATE_EMPLOYER_WORK_DAY_END("UPDATE employerworkday " +
            "SET finishTime=?, unixFinishTime=? " +
            "WHERE id = ?");

    private final String query;

     SqlQuery(final String query){
       this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
