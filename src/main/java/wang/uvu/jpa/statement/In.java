package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class In extends AbstractStatement implements Statement {

	public In(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, String[] values) {
		super(root, criteriaBuilder, fieldName, values);
	}

	@Override
	protected <T extends Comparable<? super T>> Predicate toPredicate(CriteriaBuilder criteriaBuilder, Path<T> path,
			T[] values) {
		javax.persistence.criteria.CriteriaBuilder.In<T> in = criteriaBuilder.in(path);
		for (T value : values) {
			in.value(value);
		}
		return in;
	}
}
