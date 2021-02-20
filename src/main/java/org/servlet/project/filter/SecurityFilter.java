package org.servlet.project.filter;

import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.SecurityService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        securityService = new SecurityService();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String servletPath = request.getServletPath();
        User loggedUser = securityService.getLoggedUser(request.getSession());

        if (Objects.isNull(loggedUser)) {
            response.sendRedirect( "/app/login");
            return;
        }

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
