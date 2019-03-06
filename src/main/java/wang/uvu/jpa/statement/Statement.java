package wang.uvu.jpa.statement;

import javax.persistence.criteria.Predicate;

public interface Statement {

	Predicate toPredicate();
}
