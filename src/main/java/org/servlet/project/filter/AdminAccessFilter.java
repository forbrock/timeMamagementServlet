package org.servlet.project.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/admin/*")
public class AdminAccessFilter extends HttpFilter {
    private static final Logger log = LogManager.getLogger(AdminAccessFilter.class);
    private SecurityService securityService;

    public AdminAccessFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException {
        HttpSession session = req.getSession();
        User user = securityService.getLoggedUser(session);

        try {
            if (Objects.isNull(user) || (user.getRole() != Role.ADMIN)) {
                res.sendRedirect(req.getContextPath() + "/WEB-INF/view/error.jsp");
                return;
            }
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            log.error("Authentication error", e);
        }
    }
}
