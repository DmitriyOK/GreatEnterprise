package worktime.dao.impl;

import worktime.dao.EmployerDao;
import worktime.datasource.DBConnection;
import worktime.model.Employer;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
/**
 * Реализация интерфейса {@link Employer}
 */
public class EmployerDaoImpl implements EmployerDao {

    public Employer findByLogin(String login) {
        CriteriaBuilder builder = DBConnection.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> query = builder.createQuery(Employer.class);
        Root<Employer> employer = query.from(Employer.class);
        Predicate predicate = builder.equal(employer.get("login"), login);
        query.where(predicate);

        TypedQuery<Employer> employerQuery = DBConnection.getEntityManager().createQuery(query);
        List<Employer> resultList = employerQuery.getResultList();

        return resultList.get(0);
    }

    public List<Employer> findByDepartmentName(String departmentName) {

        CriteriaBuilder builder = DBConnection.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> criteriaQuery = builder.createQuery(Employer.class);
        Root<Employer> employerRoot = criteriaQuery.from(Employer.class);
        criteriaQuery.where(builder.equal(employerRoot.get("department").get("departmentName"), departmentName));

        TypedQuery<Employer> typedQuery = DBConnection.getEntityManager().createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }
}
