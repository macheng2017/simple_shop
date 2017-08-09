package com.spzwl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class MD5Utils {
	
	public static String getMD5(String password) {
		MessageDigest message = null;
		try {
			message = MessageDigest.getInstance("md5");
			byte[] len = message.digest(password.getBytes());
			Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
