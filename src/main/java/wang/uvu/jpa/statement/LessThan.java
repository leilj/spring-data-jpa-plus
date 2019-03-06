package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LessThan extends AbstractStatement implements Statement {

	public LessThan(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, String[] values) {
		super(root, criteriaBuilder, fieldName, values);
	}

	@Override
	protected <T extends Comparable<? super T>> Predicate toPredicate(CriteriaBuilder criteriaBuilder, Path<T> path,
			T[] values) {
		Predicate conjunction = criteriaBuilder.conjunction();
		for (T value : values) {
			Predicate predicate = criteriaBuilder.lessThan(path, value);
			conjunction.getExpressions().add(predicate);
		}
		return conjunction;
	}
}
