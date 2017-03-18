package dao.impl;



import dao.EmployerWorkDayDao;
import model.Employer;
import model.EmployerWorkDay;
import factory.EntityFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class EmployerWorkDayDaoImpl implements EmployerWorkDayDao {

    //TODO затестить
    public List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod) {
        List<Object> listObjects = EntityFactory.findEmployerWorkDayHistoryByPeriod(employer, startPeriod, endPeriod);
        return convertToArrayList(listObjects);
    }

    public List<EmployerWorkDay> findAll(){
        List<Object> listObjects = EntityFactory.findAll(EmployerWorkDay.class, null);
        return convertToArrayList(listObjects);
    }

    public EmployerWorkDay findCurrentEmployerWorkDay(Employer employer) {
        List<Object> currentEmployerWorkDay = EntityFactory.findCurrentEmployerWorkDay(employer);
        return convertToArrayList(currentEmployerWorkDay).get(0);
    }

    public EmployerWorkDay save(EmployerWorkDay employerWorkDay) {
        return EntityFactory.saveEmployerWorkDay(employerWorkDay);
    }

    public List<EmployerWorkDay> currentDayReport() {
        return convertToArrayList(EntityFactory.findCurrentDayReport());
    }

    private ArrayList<EmployerWorkDay> convertToArrayList(List listObjects){
        ArrayList<EmployerWorkDay> result = new ArrayList<EmployerWorkDay>();
        for (Object object : listObjects) {
            result.add((EmployerWorkDay) object);
        }
        return result;
    }
}
