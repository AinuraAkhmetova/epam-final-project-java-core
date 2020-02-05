package homework_22.reporting;

import homework_22.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
