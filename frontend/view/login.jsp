<%@page pageEncoding="utf-8" %>
<%
    String context = request.getContextPath();
    String loginError = null;
    if(request.getAttribute("loginError") != null) {
        loginError = (String) request.getAttribute("loginError");
    }
    String logoutMessage = null;
    if(request.getAttribute("logoutMessage") != null) {
        logoutMessage = (String) request.getAttribute("logoutMessage");
    }
%>

<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <div class="py-3 text-center">
            <h2>Zaloguj się</h2>
        </div>
        <% if(loginError != null) {%>
            <div class="alert alert-danger" role="alert">
                <%=loginError%>
            </div>
        <% } %>
        <% if(logoutMessage != null) {%>
            <div class="alert alert-success" role="alert">
                <%=logoutMessage%>
            </div>
        <% } %>
        <form class="needs-validation" action="<%=context%>/login" method="post" novalidate accept-charset="UTF-8">
            <div class="form-row">
                <div class="col-3"></div>
                <div class="col-6 mb-3">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" id="login" name="login" placeholder="Login" required>
                    <div class="invalid-feedback">
                        Proszę podać login.
                    </div>
                </div>
                <div class="col-3"></div>
            </div>
            <div class="form-row">
                <div class="col-3"></div>
                <div class="col-6 mb-3">
                    <label for="password">Hasło</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Hasło" required>
                    <div class="invalid-feedback">
                        Proszę podać hasło.
                    </div>
                </div>
                <div class="col-3"></div>
            </div>
            <div class="form-row">
                <div class="col-3"></div>
                <div class="col-6 mb-3">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Zaloguj</button>
                </div>
                <div class="col-3"></div>
        </form>
    </div>
    <div class="col-3"></div>
</div>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>