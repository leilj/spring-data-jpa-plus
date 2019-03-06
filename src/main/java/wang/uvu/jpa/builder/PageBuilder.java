package wang.uvu.jpa.builder;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import wang.uvu.jpa.base.Query;


public class PageBuilder {

	public static PageRequest build(Query query) {
		List<Order> orders = OrderBuilder.build(query);
		if (!orders.isEmpty()) {
			return new PageRequest((query.getPage_()), query.getSize_(),
					new Sort(orders.toArray(new Order[orders.size()])));
		}
		return new PageRequest((query.getPage_()), query.getSize_());
	}
}
