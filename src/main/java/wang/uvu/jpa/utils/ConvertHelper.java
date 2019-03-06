package wang.uvu.jpa.utils;


import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;

public class ConvertHelper {

	private static final DateConverter dateConverter = new DateConverter();

	static {
		dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMdd", "yyyyMMddHHmmss" });
		ConvertUtils.register(dateConverter, Date.class);
	}

	public static Object[] convert(String[] sources, Class<?> target) {
		return (Object[]) ConvertUtils.convert(sources, target);
	}

	public static String convert(Object value) {
		return ConvertUtils.convert(value);
	}
}
