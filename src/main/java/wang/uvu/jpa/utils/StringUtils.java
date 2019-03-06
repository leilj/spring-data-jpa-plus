package wang.uvu.jpa.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public final class StringUtils {

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static String join(Object[] array) {
		return join(array, ",");
	}

	public static String join(Object[] array, String c) {
		StringBuffer buf = new StringBuffer(array.length * 2);
		for (int i = 0; i < array.length; i++) {
			buf.append(array[i]).append(c);
		}
		buf.setLength(buf.length() - c.length());
		return buf.toString();
	}

	public static <T> String join(Collection<T> collection, String c) {
		return join(collection.toArray(), c);
	}

	public static <T> String join(Collection<T> collection) {
		return join(collection.toArray(), ",");
	}

	public static String toString(InputStream is) {
		byte[] b = new byte[1024];
		int len = 0;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			while ((len = is.read(b)) > 0) {
				out.write(b, 0, len);
			}
			return out.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String toLowerFristChar(String str) {
		char[] chars = str.toCharArray();
		if (chars[0] >= 65 && chars[0] <= 90) {// 大写
			chars[0] += 32;
		}
		return String.valueOf(chars);
	}

	public static String firstLetterToUpper(String str) {
		char[] array = str.toCharArray();
		if (array[0] >= 'a' && array[0] <= 'z') {
			array[0] -= 32;
		}
		return String.valueOf(array);
	}
}
