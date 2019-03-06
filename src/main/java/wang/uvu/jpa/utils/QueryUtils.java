package wang.uvu.jpa.utils;

import static wang.uvu.jpa.utils.Operators.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wang.uvu.jpa.base.Query;


public class QueryUtils {

	public static final List<String> IGNORES = Arrays.asList("orders_", "page_", "size_", "or_", "querys_", "fields_");

	public static final List<String> OPERATOR_ALL = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add(EQUAL);
			add(GREATER_THAN);
			add(GREATER_THAN_OR_EQUALTO);
			add(LESS_THAN);
			add(LESS_THAN_OR_EQUALTO);
			add(LIKE);
			add(NOT_LIKE);
			add(NOT_EQUAL);
			add(IS_NOT_NULL);
			add(IS_NULL);
			add(IN);
			add(NOT_IN);
			add(IS_EMPTY);
			add(IS_NOT_EMPTY);
			Collections.sort(this, new Comparator<String>() {
				public int compare(String left, String right) {
					return right.length() - left.length();
				}
			});
		}
	};

	private static Class<?> getSupportType(Class<?> type) {
		switch (type.getTypeName()) {
		case "int":
			return Integer.class;
		case "short":
			return Integer.class;
		case "boolean":
			return Boolean.class;
		case "long":
			return Long.class;
		default:
			return type;
		}
	}

	public static Class<?> getFieldType(Class<?> clz, String fieldName) {
		return getSupportType(getField(clz, fieldName).getType());
	}

	public static Field getField(Class<?> clz, String fieldName) {
		try {
			return clz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			if (clz != Object.class)
				return getField(clz.getSuperclass(), fieldName);
			throw new RuntimeException(e);
		}
	}

	public static String getOperator(String s) {
		for (String operator : OPERATOR_ALL) {
			if (s.startsWith(operator)) {
				return operator;
			}
		}
		return EQUAL;
	}

	public static String[] getValues(String expression, String operator) {
		for (String s : OPERATOR_ALL) {
			if (expression.startsWith(s)) {
				expression = expression.replace(s, "");
				break;
			}
		}
		return getValues(expression);
	}

	private static String[] getValues(String expression) {
		if (!expression.startsWith("'")) {
			return expression.split(",");
		}
		String reg = "('+?)(.*?)('+?)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(expression);
		List<String> values = new ArrayList<String>();
		while (matcher.find()) {
			String value = matcher.group(2);
			values.add(value);
		}
		return values.toArray(new String[values.size()]);
	}

	public static boolean ignore(String name) {
		return IGNORES.contains(name);
	}

	/**
	 * 会忽略掉·关键字·字段
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> describe(Query query) {
		Field[] fields = query.getClass().getDeclaredFields();
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : fields) {
			Object value;
			try {
				field.setAccessible(true);
				value = field.get(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			String name = field.getName();
			if (value == null || ignore(name)) {
				continue;
			}
			if (field.getType().isArray()) {
				map.put(name, StringUtils.join((Object[]) value));
			} else if (field.getType().isAssignableFrom(Collection.class)) {
				map.put(name, StringUtils.join((Collection<Object>) value));
			} else {
				map.put(name, ConvertHelper.convert(value));
			}
		}
		return map;
	}
}
