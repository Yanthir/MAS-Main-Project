package mas.controller;

import mas.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = AuthService.getEmployeeIdForAuth(
                request.getParameter("login"),
                request.getParameter("password")
        );
        if(userId == null) {
            request.setAttribute("loginError", "Błąd logowania! Nie ma użytkownika o takiej kombinacji loginu i hasła.");
            request.setAttribute("pageName", "login");
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("userId", userId);
            request.setAttribute("pageName", "dashboard");
            request.setAttribute("tab", "dashboard");
            request.getRequestDispatcher("/dashboard/view").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
