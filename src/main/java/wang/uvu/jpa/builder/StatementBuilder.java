package wang.uvu.jpa.builder;

import static wang.uvu.jpa.utils.Operators.*;

import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import wang.uvu.jpa.base.Query;
import wang.uvu.jpa.statement.*;
import wang.uvu.jpa.utils.Operators;
import wang.uvu.jpa.utils.QueryUtils;
import wang.uvu.jpa.utils.StringUtils;

public class StatementBuilder {

	public static Statement build(Root<?> root, CriteriaBuilder criteriaBuilder, Query query) {
		Map<String, String> map = QueryUtils.describe(query);
		Statements statements = new Statements(criteriaBuilder, query.isOr_());
		Set<String> keySet = map.keySet();
		for (String fieldName : keySet) {
			String rootExpression = map.get(fieldName);
			if (StringUtils.isBlank(rootExpression)) {
				continue;
			}
			Statements statement = buildStatements(root, criteriaBuilder, fieldName, rootExpression);
			statements.add(statement);
		}
		return statements;
	}

	private static Statements buildStatements(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, String rootExpression) {
		Statements rootStatements = new Statements(criteriaBuilder, true);
		String[] groupExpressions  = rootExpression.split("\\" + Operators.OR);
		for (String groupExpression : groupExpressions) {
			String[] plainExpressions = groupExpression.split(Operators.AND);
			Statements statements = new Statements(criteriaBuilder);
			for (String plainExpression : plainExpressions) {
				Statement statement = buildStatement(root, criteriaBuilder, fieldName, plainExpression);
				statements.add(statement);
			}
			rootStatements.add(statements);
		}
		return rootStatements;
	}

	private static Statement buildStatement(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, String plainExpression) {
		String operator = QueryUtils.getOperator(plainExpression);
		String[] values = QueryUtils.getValues(plainExpression, operator);
		switch (operator) {
		case EQUAL:
			if (values.length > 1) {
				return new In(root, criteriaBuilder, fieldName, values);
			}
			return new Equal(root, criteriaBuilder, fieldName, values);
		case GREATER_THAN:
			return new GreaterThan(root, criteriaBuilder, fieldName, values);
		case GREATER_THAN_OR_EQUALTO:
			return new GreaterThanOrEqualTo(root, criteriaBuilder, fieldName, values);
		case LESS_THAN:
			return new LessThan(root, criteriaBuilder, fieldName, values);
		case LESS_THAN_OR_EQUALTO:
			return new LessThanOrEqualTo(root, criteriaBuilder, fieldName, values);
		case LIKE:
			return new Like(root, criteriaBuilder, fieldName, values);
		case NOT_LIKE:
			return new NotLike(root, criteriaBuilder, fieldName, values);
		case NOT_EQUAL:
			return new NotEqual(root, criteriaBuilder, fieldName, values);
		case IS_NOT_NULL:
			return new IsNotNull(root, criteriaBuilder, fieldName);
		case IS_NULL:
			return new IsNull(root, criteriaBuilder, fieldName);
		case IN:
			return new In(root, criteriaBuilder, fieldName, values);
		case NOT_IN:
			return new NotIn(root, criteriaBuilder, fieldName, values);
		case IS_EMPTY:
			return new IsEmpty(root, criteriaBuilder, fieldName);
		case IS_NOT_EMPTY:
			return new IsNotEmpty(root, criteriaBuilder, fieldName);
		default:
			if (values.length > 1) {
				return new In(root, criteriaBuilder, fieldName, values);
			}
			return new Equal(root, criteriaBuilder, fieldName, values);
		}
	}
}
