package wang.uvu.jpa.statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wang.uvu.jpa.utils.ConvertHelper;
import wang.uvu.jpa.utils.QueryUtils;


public abstract class AbstractStatement implements Statement {

	private Root<?> root;
	private CriteriaBuilder criteriaBuilder;
	private String fieldName;
	private String[] values;

	public AbstractStatement(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, String[] values) {
		this.root = root;
		this.criteriaBuilder = criteriaBuilder;
		this.fieldName = fieldName;
		this.values = values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Predicate toPredicate() {
		Object[] sqlValues = ConvertHelper.convert(values, QueryUtils.getFieldType(root.getJavaType(), fieldName));
		return toPredicate(criteriaBuilder, root.<Comparable<? super Object>>get(fieldName),
				(Comparable<? super Object>[]) sqlValues);
	}

	protected abstract <T extends Comparable<? super T>> Predicate toPredicate(CriteriaBuilder criteriaBuilder,
			Path<T> path, T[] values);
}
