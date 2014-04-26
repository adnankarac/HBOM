package me.ak.utils;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	public static String byteToString(byte[] arr) {
		try {
			return new String(arr, "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
