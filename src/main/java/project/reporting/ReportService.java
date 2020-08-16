package project.reporting;

import project.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
