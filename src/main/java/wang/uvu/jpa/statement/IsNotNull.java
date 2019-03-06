package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsNotNull implements Statement {

	private Root<?> root;
	private CriteriaBuilder criteriaBuilder;
	private String fieldName;

	public IsNotNull(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName) {
		this.root = root;
		this.criteriaBuilder = criteriaBuilder;
		this.fieldName = fieldName;
	}

	@Override
	public Predicate toPredicate() {
		return new IsNull(root, criteriaBuilder, fieldName).toPredicate().not();
	}
}
