<%@page pageEncoding="utf-8" %>
<%
    String pageName = (String) request.getAttribute("pageName");
%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<body style="background-color: lavender">
    <div class="container h-100 bg-white">
        <div class="row bg-light border-bottom">
            <div class="col-12 mx-3 mb-5 pt-5">
                <h1>Rozlewnia soków</h1>
            </div>
        </div>
        <% if(!"login".equals(pageName) && !"error".equals(pageName)) { %>
            <jsp:include page="navbar/navbar.jsp"/>
        <% } %>
        <div class="row pt-2">
            <div class="col-12">
                <% if("login".equals(pageName)) {%>
                    <jsp:include page="view/login.jsp"/>
                <%} else if("error".equals(pageName)) {%>
                    <jsp:include page="view/error.jsp"/>
                <%} else if("dashboard".equals(pageName)) {%>
                    <jsp:include page="view/dashboard.jsp"/>
                <%} else if("reportForm".equals(pageName)) {%>
                    <jsp:include page="form/report.jsp"/>
                <%} else if("reportView".equals(pageName)) {%>
                    <jsp:include page="data/report.jsp"/>
                <%} else {%>
                    <jsp:include page="view/error.jsp"/>
                <% } %>
            </div>
        </div>
    </div>
</body>