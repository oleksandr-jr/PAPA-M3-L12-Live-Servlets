package org.javarush.oleksandr.l12live.profile;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.javarush.oleksandr.l12live.profile.repository.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "UserExistsFilter", urlPatterns = {"/register/*"})
public class UserExistsFilter implements Filter {
    UserRepository userRepository = UserRepository.getInstance();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String userName = request.getParameter("userName");

        PrintWriter writer = response.getWriter();

        System.out.println(userRepository.contains(userName));

        if (userRepository.contains(userName)) {
            writer.println("<html><body>User already exists</body></html>");
            System.out.println("Filter out");
            return;
        }

        chain.doFilter(request, response);
    }
}
