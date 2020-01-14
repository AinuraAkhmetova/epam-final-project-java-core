package homework_14.reporting;

import homework_14.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
