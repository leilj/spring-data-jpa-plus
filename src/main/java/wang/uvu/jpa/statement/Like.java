package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Like implements Statement {

	private Root<?> root;
	private CriteriaBuilder criteriaBuilder;
	private String[] values;
	private String fieldName;

	public Like(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, String[] values) {
		this.root = root;
		this.criteriaBuilder = criteriaBuilder;
		this.values = values;
		this.fieldName = fieldName;
	}

	@Override
	public Predicate toPredicate() {
		Predicate conjunction = criteriaBuilder.conjunction();
		for (String value : values) {
			Predicate predicate = criteriaBuilder.like(root.<String>get(fieldName), value);
			conjunction.getExpressions().add(predicate);
		}
		return conjunction;
	}
}
