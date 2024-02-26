package com.example.HRM;

import java.security.SecureRandom;
import java.util.Base64;
public class SecretKeyGenerator {
	public static void main(String[] args) {
	    int keyLengthBytes = 32;
	    byte[] secretKeyBytes = generateSecureRandomKey(keyLengthBytes);
	    String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);
	    System.out.println("Generated Secret Key: " + secretKey);
	  }
	  
	  public static byte[] generateSecureRandomKey(int keyLengthBytes) {
	    SecureRandom secureRandom = new SecureRandom();
	    byte[] key = new byte[keyLengthBytes];
	    secureRandom.nextBytes(key);
	    return key;
	  }
	
}
