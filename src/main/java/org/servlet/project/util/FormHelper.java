package org.servlet.project.util;

import javax.servlet.http.HttpServletRequest;

public class FormHelper {

    public static void fillAdminUserEditForm(HttpServletRequest request,
                                             String firstName, String lastName, String role) {
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("role", role);
    }

    public static void fillUserCreationForm(HttpServletRequest request,
                                      String firstName, String lastName, String email) {
        request.setAttribute("first_name", firstName);
        request.setAttribute("last_name", lastName);
        request.setAttribute("email", email);
    }
}
