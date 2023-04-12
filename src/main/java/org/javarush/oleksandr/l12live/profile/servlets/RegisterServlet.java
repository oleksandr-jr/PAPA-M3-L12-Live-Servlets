package org.javarush.oleksandr.l12live.profile.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.javarush.oleksandr.l12live.profile.repository.User;
import org.javarush.oleksandr.l12live.profile.repository.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/register")

public class RegisterServlet extends HttpServlet {

    UserRepository userRepository = UserRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();

        writer.println("<html><body>");
        writer.println("<h1>Register</h1>");
        writer.println("<hr>");
        writer.println("<form method='post'>");
        writer.println("<p>Name: </p>");
        writer.println("<input type='text' name='name'>");
        writer.println("<p>Username: </p>");
        writer.println("<input type='text' name='userName'>");
        writer.println("<p>Email: </p>");
        writer.println("<input type='email' name='email'>");
        writer.println("<p>Password: </p>");
        writer.println("<input type='password' name='password'>");
        writer.println("<br>");
        writer.println("<input type='submit' value='Register'>");
        writer.println("<br>");
        writer.println("<a href='login'>Login page</a>");
        writer.println("</form>");

        writer.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, userName, email, password);

        userRepository.add(user);

        PrintWriter writer = response.getWriter();
//        response.sendRedirect("login");

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login");
        requestDispatcher.forward(request, response);


    }
}
