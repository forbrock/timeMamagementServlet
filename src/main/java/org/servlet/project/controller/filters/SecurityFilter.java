package org.servlet.project.controller.filters;

import org.servlet.project.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    private final List<String> unauthorizedPaths = Arrays.asList("/login", "/registration");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getServletPath();

        if (isPublicAsset(request) || unauthorizedPaths.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (isAuthenticated(request) && isActive(request)) {
            chain.doFilter(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }

    private boolean isActive(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        if (Objects.isNull(user)) {
            return false;
        }
        return user.isEnabled();
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession(false) != null
                && request.getSession().getAttribute("loggedUser") != null;
    }

    private boolean isPublicAsset(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/public") || path.contains("/favicon.ico");
    }
}
