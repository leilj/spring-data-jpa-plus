package wang.uvu.jpa.utils;

import java.util.Arrays;
import java.util.List;

public final class QueryKeyword {
	
	public static String ORDERS = "orders_";
	public static String PAGE = "page_";
	public static String SIZE = "size_";
	public static String OR = "or_";
	public static String QUERYS = "querys_";
	public static String FIELDS = "fields_";

	public static final List<String> ALL = Arrays.asList(ORDERS, PAGE, SIZE, OR, QUERYS, FIELDS);
}
