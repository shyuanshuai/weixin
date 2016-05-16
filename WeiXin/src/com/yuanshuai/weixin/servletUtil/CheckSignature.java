package com.yuanshuai.weixin.servletUtil;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckSignature {

	private static final String token = "weixin";

	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {

		String[] array = new String[] { token, timestamp, nonce };

		// 第一步、排序
		Arrays.sort(array);

		// 第二步、将数组倒序
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			content.append(array[i]);
		}

		// 第三步、用sha1加密
		String temp = content.toString();

		if (temp == null || temp.length() == 0) {
			return false;
		}

		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(temp.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}

			String result = new String(buf);

			return result.equals(signature);
		} catch (Exception e) {
			return false;
		}
	}

}
