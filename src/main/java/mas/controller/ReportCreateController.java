package mas.controller;

import mas.mapper.BatchMapper;
import mas.mapper.ReportMapper;
import mas.model.business.Batch;
import mas.model.business.Report;
import mas.model.dto.BatchDTO;
import mas.model.dto.ReportDTO;
import mas.service.ReportService;
import mas.util.StatusUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

public class ReportCreateController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageName", "dashboard");
        request.setAttribute("tab", "dashboard");

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setBatchId(request.getParameter("batchId"));
        reportDTO.setCreateDate(new Date());
        reportDTO.setDescription(new String(request.getParameter("description").getBytes("ISO-8859-1"), Charset.forName("UTF-8")));
        reportDTO.setEmployeeId((String) request.getSession().getAttribute("employeeId"));

        Batch batch = ((Batch)Batch.getById(Batch.class, reportDTO.getBatchId()));
        String status = request.getParameter("status");
        if (batch != null) {
            batch.setStatus(StatusUtil.getStatusById(status));
        } else {
            request.setAttribute("message", "Błąd! Raport nie został dodany.");
            request.setAttribute("messageType", "danger");
            request.getRequestDispatcher("/main.jsp").forward(request, response);
            return;
        }

        BatchMapper.updateBatch(new BatchDTO(batch));
        ReportService.addNewReport(reportDTO);

        request.setAttribute("message", "Raport został dodany!");
        request.setAttribute("messageType", "success");
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
