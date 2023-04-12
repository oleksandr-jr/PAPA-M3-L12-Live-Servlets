package org.javarush.oleksandr.l12live;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String[]> parameterMap = request.getParameterMap();



        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Params table</h1>");
        out.println("<table border='3px' width='300'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Key</th>");
        out.println("<th>Params</th>");
        out.println("</tr>");
        out.println("</thead>");

        out.println("<tbody>");

        parameterMap.forEach((key, params) -> {
            out.println("<tr>");
            out.println("<td>" + key + "</td>");
            out.println("<td>" + Arrays.toString(params) + "</td>");
            out.println("</tr>");
        });

        out.println("</tbody>");

        out.println("</table>");
        out.println("</body></html>");
    }

}