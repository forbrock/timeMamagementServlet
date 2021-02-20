package org.servlet.project.util;

public class ViewResolver {
    public static String resolve(String page) {
        return String.format("/WEB-INF/view/%s.jsp", page);
    }
}
