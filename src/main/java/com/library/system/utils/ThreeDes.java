package com.library.system.utils;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 3DES加密
 * 
 * @author uatxz990078
 *
 */
public class ThreeDes {
	final static Logger logger = LoggerFactory.getLogger(ThreeDes.class);
	private static final String algorithm = "DESede";// 定义加密算法
	private static final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79,
			0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 };// 密钥，长度为24字节

	/**
	 * 加密
	 * 
	 * @param keybyte
	 *            加密密钥，长度为24字节
	 * @param src
	 * @return
	 */
	private static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(keybyte, algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			logger.error("3Des加密失败");
			logger.error("3Des加密失败", e);
		}
		return null;

	}

	/**
	 * 解密
	 * 
	 * @param keybyte
	 *            解密密钥，长度为24字节
	 * @param src
	 * @return
	 */
	private static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(keybyte, algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			logger.error("3Des解密失败");
			logger.error("3Des解密失败", e);
		}
		return null;
	}

	// // 转化成16进制字符串
	// private static String byte2hex(byte[] b) {
	// String hs = "";
	// String stmp = "";
	//
	// for (int n = 0; n < b.length; n++) {
	// stmp = Integer.toHexString(b[n] & 0xFF);
	// if (stmp.length() == 1) {
	// hs = hs + "0" + stmp;
	// } else {
	// hs = hs + stmp;
	// }
	// if (n < b.length - 1) {
	// hs = hs + ":";
	// }
	// }
	// return hs.toUpperCase();
	// }

	/**
	 * 获取加密后的字符串
	 * 
	 * @param src
	 *            明文
	 * @return 加密后的字符串
	 */
	public static String encrypt(String src) {
		byte[] encoded = encryptMode(keyBytes, src.getBytes());
		// 使用Base64，将byte[]转换为字符串
		String encodedStr = Base64.encodeBase64String(encoded);
		return encodedStr;
	}

	/**
	 * 根据密文获取明文
	 * 
	 * @param encoded
	 *            密文
	 * @return 明文字符串
	 */
	public static String decrypt(String encoded) {
		// 使用Base64，将字符串转换为byte[]
		byte[] encodedBytes = Base64.decodeBase64(encoded);
		byte[] decodedBytes = decryptMode(keyBytes, encodedBytes);
		String decodedStr = new String(decodedBytes);
		return decodedStr;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String szSrc = "This is a 3DES test. 测试sd33";
		System.out.println("加密前字符串：" + szSrc);

		String encodedStr = encrypt(szSrc);
		System.out.println("加密后字符串：" + new String(encodedStr));

		String decodedStr = decrypt(encodedStr);
		System.out.println("解密后字符串：" + decodedStr);
	}
}
