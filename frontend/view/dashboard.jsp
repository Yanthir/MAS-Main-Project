<%@ page import="mas.model.business.Employee" %>
<%@page pageEncoding="utf-8" %>

<%
    if(session.getAttribute("userId") == null) {
        request.getRequestDispatcher("/error").forward(request, response);
        return;
    }

    String fullName;
    Employee employee = (Employee) Employee.getById(Employee.class, (String) request.getSession().getAttribute("userId"));
    fullName = employee.getName() + " " + employee.getSurname();

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