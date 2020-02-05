package homework_19.reporting;

import homework_19.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
