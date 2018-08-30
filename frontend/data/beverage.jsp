<%@ page import="associations.AssociationException" %>
<%@ page import="mas.exception.BusinessException" %>
<%@ page import="mas.model.business.Batch" %>
<%@ page import="mas.model.business.Beverage" %>
<%@ page import="mas.model.business.Order" %>
<%@ page import="mas.model.business.Template" %>
<%@ page import="mas.model.constants.AssociationNames" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@page pageEncoding="utf-8" %>

<%
    if(session.getAttribute("clientId") == null || request.getAttribute("orderId") == null) {
        request.getRequestDispatcher("/error").forward(request, response);
        return;
    }
    List<Beverage> beverages;
    Order order = (Order) Order.getById(Order.class, (String) request.getAttribute("orderId"));
    String header = "Zamówienie #" + order.getId();
    try {
        beverages = Arrays.stream(order.getLinkedObjects(AssociationNames.ASSOC_ORDER_BEVERAGES))
                .map(Beverage.class::cast)
                .collect(Collectors.toList());
    } catch (AssociationException e) {
        throw new BusinessException(e);
    }
    beverages.sort(Comparator.comparing(Beverage::getCreateDate).reversed());
%>

<div class="py-3 text-center">
    <h2><%=header%></h2>
</div>

<table class="table">
    <jsp:include page="header/beverage.jsp"/>
    <% for(int i = 0; i < beverages.size(); i++) {
        Beverage beverage = beverages.get(i);
        Batch batch;
        try {
            batch = (Batch) beverage.getLinkedObjects(AssociationNames.ASSOC_BEVERAGE_BATCH)[0];
        } catch (AssociationException e) {
            throw new BusinessException(e);
        }
        Template template;
        try {
            template = (Template) beverage.getLinkedObjects(AssociationNames.ASSOC_BEVERAGE_TEMPLATE)[0];
        } catch (AssociationException e) {
            throw new BusinessException(e);
        }
    %>
        <tr>
            <td scope="row">
                <%=i+1%>
            </td>
            <td>
                <%=beverage.getId()%>
            </td>
            <td>
                <%=batch.getId()%>
            </td>
            <td>
                <%=beverage.getSubmissionDateFormatted()%>
            </td>
            <td>
                <%=template.getPrice()%>
            </td>
        </tr>
    <% } %>
</table>