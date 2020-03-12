package fis.com.vn.common;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class Common {
	public static String getMD5(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

			return myHash;
		} catch (Exception e) {
		}
		return null;
	}
}
