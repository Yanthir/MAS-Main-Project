<%@ page import="mas.model.constants.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="mas.model.business.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%@page pageEncoding="utf-8" %>

<%
    boolean isEmployee = session.getAttribute("employeeId") != null;
    boolean isClient = session.getAttribute("clientId") != null;
    if(!isEmployee && !isClient) {
        request.getRequestDispatcher("/error").forward(request, response);
        return;
    }
    String currentTab = null;
    if(request.getAttribute("tab") != null) {
        currentTab = (String) request.getAttribute("tab");
    }
    String context = request.getContextPath();
    List<Role> roles = new ArrayList<>();
    if(isEmployee) {
        roles = ((Employee) Employee.getById(Employee.class, (String) session.getAttribute("employeeId"))).getRoles();
    }
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav w-100">
        <li class="nav-item <%="dashboard".equals(currentTab) ? "active" : ""%>">
            <a class="nav-link" href="<%=context%>/dashboard/view">Tablica</a>
        </li>
        <% if(roles.contains(Role.CONTROLLER)) { %>
            <li class="nav-item dropdown <%="report".equals(currentTab) ? "active" : ""%>">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Raport
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="<%=context%>/report/view">Moje raporty</a>
                    <a class="dropdown-item" href="<%=context%>/report/view/all">Wszystkie raporty</a>
                    <a class="dropdown-item" href="<%=context%>/report/form">Nowy raport</a>
                </div>
            </li>
        <% } %>
        <% if(isClient) { %>
            <li class="nav-item <%="orders".equals(currentTab) ? "active" : ""%>">
                <a class="nav-link" href="<%=context%>/order/view">Moje zamówienia</a>
            </li>
        <% } %>
        <li class="nav-item ml-auto">
            <a class="nav-link" href="<%=context%>/logout">Wyloguj</a>
        </li>
    </ul>
</nav>