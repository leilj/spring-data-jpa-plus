package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsNotEmpty implements Statement {

	private Root<?> root;
	private CriteriaBuilder criteriaBuilder;
	private String fieldName;

	public IsNotEmpty(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName) {
		this.root = root;
		this.criteriaBuilder = criteriaBuilder;
		this.fieldName = fieldName;
	}

	@Override
	public Predicate toPredicate() {
		Predicate isNotNull = new IsNotNull(root, criteriaBuilder, fieldName).toPredicate();
		Predicate notEqual = new NotEqual(root, criteriaBuilder, fieldName, new String[] { "" }).toPredicate();
		Predicate conjunction = criteriaBuilder.conjunction();
		conjunction.getExpressions().add(isNotNull);
		conjunction.getExpressions().add(notEqual);
		return conjunction;
	}
}
