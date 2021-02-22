package org.servlet.project.util;

public class ViewResolver {

    public static String resolve(String page) {
        return String.format("/WEB-INF/view/%s.jsp", page);
    }

    public static String resolveAdmin(String page) {
        return String.format("/WEB-INF/view/admin/%s.jsp", page);
    }
}
