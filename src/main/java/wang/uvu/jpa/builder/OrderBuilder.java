package wang.uvu.jpa.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import wang.uvu.jpa.base.Query;
import wang.uvu.jpa.utils.Operators;
import wang.uvu.jpa.utils.StringUtils;


public class OrderBuilder {
	
	private static final String  KEY_REGEX = "^[" + Operators.SORT_ASC + "|" + Operators.SORT_DESC + "]";

	public static List<Order> build(Query query) {
		List<Order> orderList = new ArrayList<Order>();
		String orders = query.getOrders_();
		if(!StringUtils.isBlank(orders)){
			String[] vars = orders.split(",");
			for (String sort : vars) {
				String name = sort.replaceAll(KEY_REGEX, "");
				Direction direction = sort.startsWith(Operators.SORT_DESC) ? Direction.DESC : Direction.ASC;
				Order _order = new Order(direction, name);
				orderList.add(_order);
			}
		}
		return orderList;
	}
}
