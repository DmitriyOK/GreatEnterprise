package dao.impl;



import dao.EmployerWorkDayDao;
import model.Employer;
import model.EmployerWorkDay;
import factory.impl.EntityFactory;
import model.ReportCurrentDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса {@link EmployerWorkDayDao}
 *
 * Загружает объекты из базы данных и приводит к конкретному типу.
 * Результат представлен как {@link ArrayList} объектов
 * {@link EmployerWorkDay} и {@link ReportCurrentDay}.
 *
 */
public class EmployerWorkDayDaoImpl implements EmployerWorkDayDao {

   private final EntityFactory ENTITY_FACTORY = new EntityFactory();

    public List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod) {
        List<Object> result = ENTITY_FACTORY.findEmployerWorkDayHistoryByPeriod(employer, startPeriod, endPeriod);
        return convertToArrayList(result);
    }

    public List<ReportCurrentDay> findBySelectedPeriod(int selectedUnixTimeDay) {
        List<Object> result = ENTITY_FACTORY.findSelectedDayReport(selectedUnixTimeDay);
        return toArrayList(result);
    }

    public EmployerWorkDay findCurrentEmployerWorkDay(Employer employer) {
        List<Object> result = ENTITY_FACTORY.findCurrentEmployerWorkDay(employer);
        return convertToArrayList(result).get(0);
    }

    public List<ReportCurrentDay> findCurrentDayReport() {
        return toArrayList(ENTITY_FACTORY.findCurrentDayReport());
    }

    public EmployerWorkDay save(EmployerWorkDay employerWorkDay) {
        return ENTITY_FACTORY.saveEmployerWorkDay(employerWorkDay);
    }


    public EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay) {
        return ENTITY_FACTORY.updateStatus(employerWorkDay);
    }

    private List<EmployerWorkDay> convertToArrayList(List listObjects){
        List<EmployerWorkDay> result = new ArrayList<EmployerWorkDay>();
        for (Object object : listObjects) {
            result.add((EmployerWorkDay) object);
        }
        return result;
    }

    private List<ReportCurrentDay> toArrayList(List listObjects){
        List<ReportCurrentDay> result = new ArrayList<ReportCurrentDay>();
        for (Object object : listObjects) {
            result.add((ReportCurrentDay) object);
        }
        return result;
    }
}
