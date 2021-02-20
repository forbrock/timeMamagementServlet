package org.servlet.project.controller.filters;

import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.SecurityService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebFilter("/app/*")
public class SecurityFilter implements Filter {
    private SecurityService securityService;
    private final List<String> unauthorizedPaths = Arrays.asList("/login", "/registration");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        securityService = new SecurityService();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI()
                .replaceFirst(request.getContextPath() + "/app", "");
        User loggedUser = securityService.getLoggedUser(request.getSession());

        if (Objects.isNull(loggedUser)) {
            if (unauthorizedPaths.contains(uri)) {
                chain.doFilter(request, response);
                return;
            } else {
                response.sendRedirect(
                        request.getContextPath() + request.getServletPath() + "/login");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
