<%@ page import="associations.AssociationException" %>
<%@ page import="mas.exception.BusinessException" %>
<%@ page import="mas.model.business.Beverage" %>
<%@ page import="mas.model.business.Client" %>
<%@ page import="mas.model.business.Order" %>
<%@ page import="mas.model.constants.AssociationNames" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@page pageEncoding="utf-8" %>

<%
    if(session.getAttribute("clientId") == null) {
        request.getRequestDispatcher("/error").forward(request, response);
        return;
    }
    List<Order> orders;
    String context = request.getContextPath();
    String header = "Moje zamówienia";
    Client client = (Client) Client.getById(Client.class, (String) request.getSession().getAttribute("clientId"));
    try {
        orders = Arrays.stream(client.getLinkedObjects(AssociationNames.ASSOC_CLIENT_ORDERS))
                .map(Order.class::cast)
                .collect(Collectors.toList());
    } catch (AssociationException e) {
        throw new BusinessException(e);
    }
    orders.sort(Comparator.comparing(Order::getSubmissionDate).reversed());
%>

<div class="py-3 text-center">
    <h2><%=header%></h2>
</div>

<table class="table">
    <jsp:include page="header/order.jsp"/>
    <% for(int i = 0; i < orders.size(); i++) {
        Order order = orders.get(i);
        List<Beverage> beverages;
        try {
            beverages = Arrays.stream(order.getLinkedObjects(AssociationNames.ASSOC_ORDER_BEVERAGES))
                    .map(Beverage.class::cast)
                    .collect(Collectors.toList());
        } catch (AssociationException e) {
            throw new BusinessException(e);
        }
    %>
    <form id="beverageView" action="<%=context%>/beverage/view" method="post">
        <input type="hidden" name="orderId" value=""/>
    </form>
        <tr class="beverageLink linkRow" orderId="<%=order.getId()%>">
            <td scope="row">
                <%=i+1%>
            </td>
            <td>
                <%=order.getId()%>
            </td>
            <td>
                <%=order.getSubmissionDateFormatted()%>
            </td>
            <td>
                <%=order.getDeliveryDateFormatted()%>
            </td>
            <td>
                <%=beverages.size()%>
            </td>
        </tr>
    <% } %>
</table>

<script>
    $(".beverageLink").click(function () {
        var orderId = $(this).attr("orderId");
        $('input[name="orderId"]').val(orderId);
        $('#beverageView').submit();
    });
</script>