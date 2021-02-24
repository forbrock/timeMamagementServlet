package org.servlet.project.util;

import javax.servlet.http.HttpServletRequest;

public class FormHelper {

    public static void fillAdminUserEditForm(HttpServletRequest request,
                                             String firstName, String lastName, String role) {
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("role", role);
    }
}
