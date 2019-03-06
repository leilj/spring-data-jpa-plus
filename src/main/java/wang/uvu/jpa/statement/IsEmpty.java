package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsEmpty implements Statement {

	private Root<?> root;
	private CriteriaBuilder criteriaBuilder;
	private String fieldName;

	public IsEmpty(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName) {
		this.root = root;
		this.criteriaBuilder = criteriaBuilder;
		this.fieldName = fieldName;
	}

	@Override
	public Predicate toPredicate() {
		Predicate isNull = new IsNull(root, criteriaBuilder, fieldName).toPredicate();
		Predicate equal = new Equal(root, criteriaBuilder, fieldName, new String[] { "" }).toPredicate();
		Predicate disjunction = criteriaBuilder.disjunction();
		disjunction.getExpressions().add(isNull);
		disjunction.getExpressions().add(equal);
		return disjunction;
	}
}
