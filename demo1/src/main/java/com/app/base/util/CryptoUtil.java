package com.app.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {
	
	
	// sha256
	public static String sha256(String password){
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			new RuntimeException("SHA-256 No Such Algorithm Error");
		}
		
		md.update(password.getBytes());
		
		// byte To Hex String
		StringBuilder sb = new StringBuilder();
		for(byte b : md.digest()) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		
		System.out.println(sb.toString().length() + ":" + sb.toString());
		
		return sb.toString();
	}
}
