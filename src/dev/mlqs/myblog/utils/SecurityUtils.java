package dev.mlqs.myblog.utils;

import java.io.*;
import java.security.*;

public class SecurityUtils {

    public static byte[] xor(byte[] array) {
        //sha256('我是一条小青龙，你是一只猪儿虫');
        byte[] b2 = "0bf1bbf2c9484236be90487214b27301ce34d5a221529d342876043a718543c2".getBytes();
        byte[] out = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            out[i] = (byte) (array[i] ^ b2[i % b2.length]);
        }
        return out;
    }

    public static String String2SHA256(String str, boolean addSalt){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            if (addSalt) hash = xor(hash);

            encdeStr = hex(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    private static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    public static String md5Hex (String message) {
        if (message == null || message.equals(""))
            return "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {}
        return null;
    }
}
