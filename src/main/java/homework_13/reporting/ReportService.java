package homework_13.reporting;

import homework_13.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
