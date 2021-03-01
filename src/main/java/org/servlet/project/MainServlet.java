package org.servlet.project;

import org.servlet.project.util.CommandResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    public void init(ServletConfig servletConfig) {
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String path = request.getRequestURI();
        path = path.replace(contextPath, "").replaceFirst("/", "");
        if (path.isEmpty()) {
            path = "index";
        }
        String page = CommandResolver.getCommand(path).execute(request);

        if (page.contains("redirect:")) {
            String redirectStr = contextPath + page.replace("redirect:", "");
            response.sendRedirect(redirectStr);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
}
