package factory;



import model.Employer;
import model.EmployerWorkDay;

import java.util.List;

/**
 *
 * Фабрика представления сущностей базы данных в виде объектов
 *
 *
 */
public interface AbstractEntityFactory {

    /**
     *
     * @param login
     * @return
     */

      List<Object> findEmployerByLogin(String login);

      List<Object> findEmployerWorkDayHistoryByPeriod(Employer employer, int startPeriod, int endPeriod);

      List<Object> findCurrentDayReport();

      List<Object> findSelectedDayReport(int selectedUnixTimeDay);

      List<Object> findCurrentEmployerWorkDay(Employer employer);

      EmployerWorkDay saveEmployerWorkDay(EmployerWorkDay employerWorkDay);

      EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay);

}
