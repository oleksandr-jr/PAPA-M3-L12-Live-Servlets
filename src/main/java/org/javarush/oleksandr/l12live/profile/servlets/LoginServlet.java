package org.javarush.oleksandr.l12live.profile.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.javarush.oleksandr.l12live.profile.repository.User;
import org.javarush.oleksandr.l12live.profile.repository.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    UserRepository userRepository = UserRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        writer.println("<html><body>");
        writer.println("<h1>Login</h1>");
        writer.println("<hr>");
        writer.println("<form method='post'>");
        writer.println("<p>Username: </p>");
        writer.println("<input type='text' name='userName'>");
        writer.println("<p>Password: </p>");
        writer.println("<input type='password' name='password'>");
        writer.println("<br>");
        writer.println("<input type='submit' value='Login'>");
        writer.println("<br>");
        writer.println("<a href='register'>Register page</a>");
        writer.println("</form>");

        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();

        if (userRepository.contains(userName)){
            User user = userRepository.get(userName);

            if (user.getPassword().equals(password)){

                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", true);
                session.setAttribute("userName", userName);
                response.sendRedirect("account");
            }else {
                writer.println("Password incorrect");
            }

        }else {
            writer.println("User does not exists");
        }

    }
}
