package com.assetmgmt.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.assetmgmt.dto.ErrorLogDto;
import com.assetmgmt.enumeration.LogOperation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AES { 

    private static SecretKeySpec secretKeySpec;  
    private static final String SECRET_KEY="664cx7899-23e26-4318-9bws9c-0062b39fe1ct";

	public static void setKey(String myKey) {		
		MessageDigest sha = null;
		try {
			byte[] key=myKey.getBytes(StandardCharsets.UTF_8);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKeySpec = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE).errorMessage(e.getMessage())
						.exception(e).build());
		}
	}

    public static String encrypt(String strToEncrypt) {
        try {
            setKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE).errorMessage(e.getMessage())
						.exception(e).build());
		}
        return null;
    }

    public static String decrypt(String strToDecrypt) throws Exception {
    	  setKey(SECRET_KEY);
          Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
          cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
          return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));     
    }
}
