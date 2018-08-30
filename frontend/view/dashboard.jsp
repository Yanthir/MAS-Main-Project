<%@ page import="mas.model.business.Employee" %>
<%@ page import="mas.model.business.Person" %>
<%@ page import="mas.model.business.Client" %>
<%@page pageEncoding="utf-8" %>

<%
    if(session.getAttribute("employeeId") == null && session.getAttribute("clientId") == null) {
        request.getRequestDispatcher("/error").forward(request, response);
        return;
    }

    String personId = (String) request.getSession().getAttribute("employeeId");
    if (personId == null) {
        personId = (String) request.getSession().getAttribute("clientId");
    }

    String fullName = "ty";
    Employee employee = (Employee) Employee.getById(Employee.class, personId);
    if(employee != null) {
        fullName = employee.getName() + " " + employee.getSurname();
    }
    Client client = (Client) Client.getById(Client.class, personId);
    if(client != null) {
        fullName = client.getName() + " " + client.getSurname();
    }

    String message = null;
    if(request.getAttribute("message") != null) {
        message = (String) request.getAttribute("message");
    }
    String messageType = "info";
    if(request.getAttribute("messageType") != null) {
        messageType = (String) request.getAttribute("messageType");
    }
%>
<div class="py5 text-center">
    <% if(message != null) {%>
        <div class="alert alert-<%=messageType%>" role="alert">
            <%=message%>
        </div>
    <% } %>
    <h2>Witaj, <%=fullName%>!</h2>
</div>