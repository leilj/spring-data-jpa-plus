package wang.uvu.jpa.statement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import lombok.Data;

@Data
public class Statements implements Statement {

	private List<Statement> statements = new ArrayList<Statement>();
	private CriteriaBuilder criteriaBuilder;
	private boolean disjunction;

	public Statements(CriteriaBuilder criteriaBuilder) {
		this.criteriaBuilder = criteriaBuilder;
		this.disjunction = false;
	}

	public Statements(CriteriaBuilder criteriaBuilder, boolean disjunction) {
		this.criteriaBuilder = criteriaBuilder;
		this.disjunction = disjunction;
	}

	public void add(Statement statement) {
		statements.add(statement);
	}

	@Override
	public Predicate toPredicate() {
		Predicate predicate = disjunction ? criteriaBuilder.disjunction() : criteriaBuilder.conjunction();
		for (Statement statement : statements) {
			predicate.getExpressions().add(statement.toPredicate());
		}
		return predicate;
	}
}
