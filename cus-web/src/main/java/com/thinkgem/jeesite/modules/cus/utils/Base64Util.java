package com.thinkgem.jeesite.modules.cus.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class Base64Util {

	private static Logger logger = Logger.getLogger(Base64Util.class);

	private final static Base64 base64 = new Base64();

	public static String encode(String val) {
		byte[] b = null;
		String s = null;
		try {
			b = val.getBytes("utf-8");
			s = new String(base64.encode(b), "utf-8");
		} catch (Exception e) {
			logger.error("Base64Util Encode Exception e=" + e.getMessage());
		}
		return s;
	}

	public static String decode(String val) {
		byte[] b = null;
		String s = null;
		try {
			b = val.getBytes("utf-8");
			s = new String(base64.decode(b), "utf-8");
		} catch (Exception e) {
			logger.error("Base64Util Decode Exception e=" + e.getMessage());
		}
		return s;
	}

	public static void main(String[] args) {
		System.out.println(decode(
				"5rWL6K+V5paH5qGj5Yqe55CG5LiA5Luj57K+5Lu/6Lqr5Lu96K+B5Yqe55CG5LiT5Lia5Zub57qn6K+B5Lmm5Ye65ZSu55yfMeS7o+i6q+S7veivgeWHuuWUruWBh+WGkuS4gOS7o+i6q+S7veivgeS4k+S4muWIoOmZpOe9keS4iuS4jeiJr+S/oeaBr+S+m+W6lOWbuuS9k+eCuOiNr+S+m+W6lOWbveS6p+aJi+aeqg=="));
	}

}
