package factory;

import model.EmployerWorkDay;

import java.util.List;

/**
 * Created by Dmitriy on 18.03.2017.
 */
public class ReportFactory implements AbstractReportFactory {

    @Override
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
