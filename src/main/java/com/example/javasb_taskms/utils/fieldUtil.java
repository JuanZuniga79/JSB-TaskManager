package com.example.javasb_taskms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fieldUtil {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static void isEmail(String email) throws Exception {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) throw new Exception("The email format is incorrect");
    }

}
