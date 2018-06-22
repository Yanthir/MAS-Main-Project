<%@page pageEncoding="utf-8" %>
<div class="py5 text-center">
    <h2>Błąd!</h2>
    <p>Aplikacja zaprowadziła cię na manowce. Odejdź, albo pozostaniesz na zawsze zagubiony!</p>

    <a href="<%=request.getContextPath()%>/welcome">
        <button class="btn btn-primary btn-lg btn-block" type="button">Przejdź do strony logowania</button>
    </a>
</div>