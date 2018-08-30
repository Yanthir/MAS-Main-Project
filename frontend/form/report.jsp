<%@ page import="mas.model.business.Batch" %>
<%@ page import="mas.model.constants.Status" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@page pageEncoding="utf-8" %>

<%
    String context = request.getContextPath();
    List<Batch> batches = Arrays.stream(Batch.getAll(Batch.class))
            .map(batch -> (Batch) batch)
            .filter(batch -> batch.getStatus().equals(Status.PENDING))
            .collect(Collectors.toList());
%>

<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <div class="py-3 text-center">
            <h2>Utwórz raport</h2>
        </div>
        <form class="needs-validation" action="<%=context%>/report/create" method="post" novalidate accept-charset="UTF-8">
            <div class="form-row">
                <div class="col-12 mb-3">
                    <label for="batchId">Numer partii</label>
                    <select class="form-control" id="batchId" name="batchId" required>
                        <% for(Batch batch : batches) { %>
                            <option><%=batch.getId()%></option>
                        <% } %>
                    </select>
                    <div class="invalid-feedback">
                        Proszę wybrać numer partii.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-12 mb-3">
                    <label for="description">Opis</label>
                    <textarea class="form-control" rows="10" id="description" maxlength="3000" name="description" required></textarea>
                    <span class="ml-auto">
                        <span id="chars">3000</span>/3000
                    </span>
                    <div class="invalid-feedback">
                        Proszę opisać raport.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-12 mb-3">
                    <label class="radio-inline"><input value="2" class="mx-2" type="radio" checked="checked" name="status">Akceptuj</label>
                    <label class="radio-inline"><input value="3" class="mx-2" type="radio" name="status">Odrzuć</label>
                    <div class="invalid-feedback">
                        Proszę wybrać status.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-3 mb-3">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Zatwierdź</button>
                </div>
            </div>
        </form>
    </div>
    <div class="col-3"></div>
</div>

<script>
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

    var maxLength = 3000;
    $('#description').keyup(function() {
        var length = $('#description').val().length;
        length = maxLength - length;
        $('#chars').text(length);
    });
</script>