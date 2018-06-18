package mas.controller;

import mas.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String personId = request.getParameter("id");
        request.setAttribute("message", PersonService.getGreetingForId(personId));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/hello.jsp");
        dispatcher.forward(request, response);
    }
}
