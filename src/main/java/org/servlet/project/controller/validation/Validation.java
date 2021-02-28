package org.servlet.project.controller.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private Map<String, String> errors;
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|" +
                    "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*" +
                    "[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4]" +
                    "[0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9]" +
                    "[0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-" +
                    "\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    public boolean isEmpty(String firstName, String lastName,
                           String email, String password, String matchingPassword) {
        errors = new HashMap<>();
        int count = 0;

        if (firstName.isEmpty()) {
            errors.put("first_name_error", "valid.reg.not.empty");
            count++;
        }

        if (lastName.isEmpty()) {
            errors.put("last_name_error", "valid.reg.not.empty");
            count++;
        }

        if (email.isEmpty()) {
            errors.put("email_error", "valid.reg.not.empty");
            count++;
        }

        if (password.isEmpty()) {
            errors.put("password_error", "valid.reg.not.empty");
            count++;
        }

        if (matchingPassword.isEmpty()) {
            errors.put("matching_password_error", "valid.reg.not.empty");
            count++;
        }
        return count > 0;
    }

    public boolean isPasswordsMatch(String pass, String confirmation) {
        boolean isMatch = pass.equals(confirmation);
        if (!isMatch) {
            errors = new HashMap<>();
            errors.put("matching_password_error", "valid.reg.password.matching");
        }
        return isMatch;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean isValidEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        boolean isMatch = matcher.matches();
        if (!isMatch) {
            errors = new HashMap<>();
            errors.put("email_error", "valid.reg.email");
        }
        return isMatch;
    }
}
