package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LessThanOrEqualTo implements Statement {

	private Root<?> root;
	private CriteriaBuilder criteriaBuilder;
	private String fieldName;
	private String[] values;

	public LessThanOrEqualTo(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, String[] values) {
		this.root = root;
		this.criteriaBuilder = criteriaBuilder;
		this.fieldName = fieldName;
		this.values = values;
	}

	@Override
	public Predicate toPredicate() {
		return new GreaterThan(root, criteriaBuilder, fieldName, values).toPredicate().not();
	}
}
