<%@ page import="associations.AssociationException" %>
<%@ page import="mas.exception.BusinessException" %>
<%@ page import="mas.model.business.Batch" %>
<%@ page import="mas.model.business.Employee" %>
<%@ page import="mas.model.business.Report" %>
<%@ page import="mas.model.constants.AssociationNames" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@page pageEncoding="utf-8" %>

<%
    if(session.getAttribute("employeeId") == null) {
        request.getRequestDispatcher("/error").forward(request, response);
        return;
    }
    List<Report> reports;
    String header;
    if(request.getAttribute("all") == null) {
        header = "Moje raporty";
        Employee employee = (Employee) Employee.getById(Employee.class, (String) request.getSession().getAttribute("employeeId"));
        try {
            reports = Arrays.stream(employee.getLinkedObjects(AssociationNames.ASSOC_CONTROLLER_REPORTS))
                    .map(Report.class::cast)
                    .collect(Collectors.toList());
        } catch (AssociationException e) {
            throw new BusinessException(e);
        }
    } else {
        header = "Wszystkie raporty";
        reports =  Arrays.stream(Report.getAll(Report.class))
                .map(Report.class::cast)
                .collect(Collectors.toList());
    }
    reports.sort(Comparator.comparing(Report::getCreateDate).reversed());
%>

<div class="py-3 text-center">
    <h2><%=header%></h2>
</div>

<table class="table">
    <jsp:include page="header/report.jsp"/>
    <% for(int i = 0; i < reports.size(); i++) {
        Report report = reports.get(i);
        Batch batch;
        try {
            batch = (Batch) report.getLinkedObjects(AssociationNames.ASSOC_REPORT_BATCH)[0];
        } catch (AssociationException e) {
            throw new BusinessException(e);
        }
    %>
        <tr>
            <td scope="row">
                <%=i+1%>
            </td>
            <td>
                <%=report.getId()%>
            </td>
            <td>
                <%=report.getCreateDateFormatted()%>
            </td>
            <td>
                <%=batch.getId()%>
            </td>
            <td>
                <div style="
                    max-height:300px;
                    overflow-y:auto;">
                        <%=report.getDescription()%>
                </div>
            </td>
            <td>
                <%=batch.getStatus().toString()%>
            </td>
        </tr>
    <% } %>
</table>