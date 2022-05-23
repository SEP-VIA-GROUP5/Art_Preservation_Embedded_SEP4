package com.via.sep4;

public class DataHandler {
    public static String changeDotToComaEmail(String email) {
        return email.replace('.', ',');
    }

    public static String convertStringArray(String[] strings) {
        StringBuilder sb = new StringBuilder();
        if (strings != null && strings.length > 0) {
            for (int i = 0; i < strings.length; i++) {
                if (i < strings.length - 1) {
                    sb.append(strings[i] + ",");
                } else {
                    sb.append(strings[i]);
                }
            }
        }
        String string = sb.toString();
        return string;
    }
}
