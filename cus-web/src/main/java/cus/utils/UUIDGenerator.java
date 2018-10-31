package cus.utils;

import java.util.UUID;

/**
 * UUIDGenerator
 */
public class UUIDGenerator {
	public static void main(String[] args) {
		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}
	}

	public static synchronized String getUUID() {
		String s = UUID.randomUUID().toString();
		s=s.replaceAll("-", "");
		return s;
	}

	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

}
