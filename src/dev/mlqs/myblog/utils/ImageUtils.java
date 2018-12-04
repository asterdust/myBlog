package dev.mlqs.myblog.utils;

public class ImageUtils {
    public static String getImg (int id) {
        return "./static/img/demo01.jpg";
    }

    public static String getImg (String id) {
        return getImg(getInt(id));
    }

    private static int getInt (String v) {
        int out = 0;
        boolean haveNum = false;
        if (v != null) {
            for (int i = 0; i < v.length(); i++) {
                if (Character.isDigit(v.charAt(i))) {
                    haveNum = true;
                    out = out * 10 + Character.getNumericValue(v.charAt(i));
                }
            }
            if (haveNum) return out;
        }
        return -1;
    }
}
