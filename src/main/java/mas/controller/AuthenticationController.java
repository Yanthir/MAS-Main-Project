package mas.controller;

import mas.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class AuthenticationController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = AuthService.getEmployeeIdForAuth(
                new String(request.getParameter("login").getBytes("ISO-8859-1"), Charset.forName("UTF-8")),
                new String(request.getParameter("password").getBytes("ISO-8859-1"), Charset.forName("UTF-8"))
        );
        String clientId = AuthService.getClientIdForAuth(
                new String(request.getParameter("login").getBytes("ISO-8859-1"), Charset.forName("UTF-8")),
                new String(request.getParameter("password").getBytes("ISO-8859-1"), Charset.forName("UTF-8"))
        );
        if(employeeId == null && clientId == null) {
            request.setAttribute("loginError", "Błąd logowania! Nie ma użytkownika o takiej kombinacji loginu i hasła.");
            request.setAttribute("pageName", "login");
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("employeeId", employeeId);
            request.getSession().setAttribute("clientId", clientId);
            request.getRequestDispatcher("/dashboard/view").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
