package net.lacit.laciteka.utilities;

import java.util.Random;

public class PasswordGen {
    // private static String base = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm~@#$%^&*()/*-+<>;:=_";
    private static String base = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

    public static String generate(int len) {

        if (base.length() == 0)
            return "";
        String password = "";
        for (int i = 0; i < len; i++) {
            password += base.toCharArray()[new Random().nextInt(base.length())];
        }
        return password;
    }
}
