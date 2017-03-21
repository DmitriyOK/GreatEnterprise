package worktime.factory;

import java.util.List;

/**
 * Фабричный интерфейс для создания отчетов.
 */
public interface AbstractReportFactory {

    /**
     * Создает отчет для заданного списка.
     *
     * @param reportTitle заголовок отчета.
     * @param results отчетный список.
     * @return готовый отчет
     */
    String buildReport(String reportTitle, List<?> results);
}
