package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsNull implements Statement {

	private Root<?> root;
	private CriteriaBuilder criteriaBuilder;
	private String fieldName;

	public IsNull(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName) {
		this.root = root;
		this.criteriaBuilder = criteriaBuilder;
		this.fieldName = fieldName;
	}

	@Override
	public Predicate toPredicate() {
		return criteriaBuilder.isNull(root.get(fieldName));
	}
}
