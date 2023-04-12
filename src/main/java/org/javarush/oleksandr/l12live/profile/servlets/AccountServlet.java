package org.javarush.oleksandr.l12live.profile.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.javarush.oleksandr.l12live.profile.repository.User;
import org.javarush.oleksandr.l12live.profile.repository.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AccountServlet", value = "/account")
public class AccountServlet extends HttpServlet {
    UserRepository userRepository = UserRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userName = (String) session.getAttribute("userName");

        User user = userRepository.get(userName);

        PrintWriter writer = response.getWriter();

        writer.println("<html><body>");
        writer.println("<h1>Account</h1>");
        writer.println("<hr>");

        writer.println("<p>Name: " + user.getName() + "</p>");
        writer.println("<p>User Name :" + user.getUserName() + "</p>");
        writer.println("<p>Email: " + user.getEmail() + "</p>");
        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
