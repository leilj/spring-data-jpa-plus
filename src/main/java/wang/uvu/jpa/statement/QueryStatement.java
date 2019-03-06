package wang.uvu.jpa.statement;

import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import org.springframework.data.domain.PageRequest;

import lombok.Data;

@Data
public class QueryStatement implements Statement {

	private Statement statement;
	private List<Selection<?>> fields;
	private PageRequest page;

	@Override
	public Predicate toPredicate() {
		return statement.toPredicate();
	}
}
