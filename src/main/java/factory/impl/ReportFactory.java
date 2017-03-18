package factory.impl;

import factory.AbstractReportFactory;
import java.util.List;

/**
 * Реализация интерфейса {@link ReportFactory}
 */
public class ReportFactory implements AbstractReportFactory {


    /**
     *  Построчное добавление объектов списка в объект {@link StringBuilder}
     *  через вызов toString() каждого объекта. Метод toString()
     *  должен быть переопределен.
     *
     * @param reportTitle - заголовок отчета.
     * @param results - отчетный список.
     * @return отчет как объект класса{@link String}
     */

    public String buildReport(String reportTitle, List<?> results) {
        StringBuilder report = new StringBuilder();
        report.append(reportTitle);
        report.append("\n");
        for (Object workDay : results) {
            report.append(workDay.toString());
            report.append("\n");
        }

        return report.toString();
    }
}
