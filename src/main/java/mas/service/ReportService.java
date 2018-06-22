package mas.service;

import mas.mapper.ReportMapper;
import mas.model.business.Report;
import mas.model.dto.ReportDTO;

import java.util.List;

public class ReportService {
    public static List<ReportDTO> loadReports() {
        List<ReportDTO> reportDTOS = ReportMapper.selectAllReports();
        for(ReportDTO ReportDTO : reportDTOS) {
            new Report(ReportDTO);
        }
        return reportDTOS;
    }
}
