package wang.uvu.jpa.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Operators {

	public static final String EQUAL = "=";
	public static final String NOT_EQUAL = "<>";

	public static final String GREATER_THAN = ">";
	public static final String GREATER_THAN_OR_EQUALTO = ">=";

	public static final String LESS_THAN = "<";
	public static final String LESS_THAN_OR_EQUALTO = "<=";

	public static final String LIKE = "*";
	public static final String NOT_LIKE = "!*";

	public static final String IS_EMPTY = "[]";
	public static final String IS_NOT_EMPTY = "![]";

	public static final String IS_NULL = "^";
	public static final String IS_NOT_NULL = "!^";

	public static final String IN = "@";
	public static final String NOT_IN = "!@";

	public static final String SORT_DESC = "-";
	public static final String SORT_ASC = "+";

	public static final String AND = "&";
	public static final String OR = "|";

	public static final List<String> ALL = new ArrayList<String>() {
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
					return left.length() - right.length();
				}
			});
		}
	};
}
