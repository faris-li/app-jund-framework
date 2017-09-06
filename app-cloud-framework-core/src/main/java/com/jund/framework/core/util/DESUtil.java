package com.jund.framework.core.util;

import static org.apache.commons.codec.binary.StringUtils.getBytesUtf8;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESUtil {
	private static Key key;		//存放密钥
	private static String KEY_STR = "LIb33G1MJlGJDX/ga2trKUabrbNRDgRd";		//用该字符串生成密钥
	
	static {
		try {
		    DESKeySpec desspec = new DESKeySpec(getBytesUtf8(KEY_STR));
		    key = SecretKeyFactory.getInstance("DES").generateSecret(desspec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对str进行DES加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncryptString(String str) {
		Base64 base64en=new Base64();  
		try {
			byte[] strBytes = str.getBytes("UTF8");		
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);	//得到加密后的字符数组
			return new String(base64en.encode(encryptStrBytes));			//使用base64进行编码
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对str进行DES解密
	 * 
	 * @param str
	 * @return
	 */
	public static String getDecryptString(String str) {	
		Base64 base64De = new Base64();
		try {
			byte[] strBytes = base64De.decode(str.getBytes());	//使用base64进行解码
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptStrBytes = cipher.doFinal(strBytes);	//根据密钥进行解码
			return new String(decryptStrBytes, "UTF8");			//进行编码转换为字符串
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
