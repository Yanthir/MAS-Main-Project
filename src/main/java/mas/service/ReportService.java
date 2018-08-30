package mas.service;

import mas.mapper.ReportMapper;
import mas.model.business.ExtendedBusinessObject;
import mas.model.business.Report;
import mas.model.dto.ReportDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportService {
    public static List<ReportDTO> loadReports() {
        List<ReportDTO> reportDTOS = ReportMapper.selectAllReports();
        for(ReportDTO ReportDTO : reportDTOS) {
            new Report(ReportDTO);
        }
        return reportDTOS;
    }

    public static void addNewReport(ReportDTO reportDTO) {
        ReportMapper.insertReport(reportDTO);
        List<ReportDTO> reportDTOS = ReportMapper.selectAllReports();
        Set<String> existingIds = Arrays.stream(Report.getAll(Report.class))
                .map(ExtendedBusinessObject::getId)
                .collect(Collectors.toSet());
        for(ReportDTO dto : reportDTOS) {
            if(existingIds.contains(dto.getId())) {
                continue;
            }
            new Report(dto).createLinks(dto);
        }
    }
}
