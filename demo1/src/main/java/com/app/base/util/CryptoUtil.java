package com.app.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 암호화 유틸
public class CryptoUtil {
    
    
    // sha256
    public static String sha256(String password) throws NoSuchAlgorithmException{
        
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        
        // byte To Hex String
        StringBuilder sb = new StringBuilder();
        for(byte b : md.digest()) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        
        // System.out.println("sha256:" + sb.toString().length() + ":" + sb.toString());
        
        return sb.toString();
    }
}
