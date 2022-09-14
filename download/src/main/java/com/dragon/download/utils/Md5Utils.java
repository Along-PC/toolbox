package com.dragon.download.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    public static String encode(String content){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(content.getBytes());
            byte[] digest = md5.digest();
            String hexString = byteToHexString(digest);
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String byteToHexString(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            int hex=aByte & 0xFF;
            String hexString = Integer.toHexString(hex);
            stringBuilder.append(hexString);
        }
        return stringBuilder.toString();
    }

}
