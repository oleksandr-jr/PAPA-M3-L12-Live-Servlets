package org.javarush.oleksandr.l12live.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TodoServlet", value = "/todo")
public class TodoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<String> todos;

        if (session.isNew()) {
            todos = new ArrayList<>();
        } else {
            todos = (List<String>) session.getAttribute("todos");
        }

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Todo app</title>");
        writer.println("</head>");

        writer.println("<body>");
        writer.println("<h1>Todo list<h1>");
        writer.println("<form method='post'>");
        writer.println("<input type='text' name='todo'>");
        writer.println("<input type='submit' value='add'>");
        writer.println("</form>");
        writer.println("<ul>");
        for (String todo : todos) {
            writer.println("<li>" + todo + "</li>");
        }
        writer.println("</ul>");
        writer.println("</body>");

        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<String> todos = (List<String>) session.getAttribute("todos");

        if (todos == null) {
            todos = new ArrayList<>();
        }

        String todo = request.getParameter("todo");

        todos.add(todo);
        session.setAttribute("todos", todos);

        response.sendRedirect(request.getContextPath() + "/todo");
    }
}
