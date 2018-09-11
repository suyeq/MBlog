package com.cyc.util;

import org.springframework.util.DigestUtils;

public class MD5 {

    public static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static boolean checkMD5(String original, String md5) {
        return getMD5(original).equals(md5);
    }
}
