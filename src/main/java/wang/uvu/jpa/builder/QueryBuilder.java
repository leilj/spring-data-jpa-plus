package wang.uvu.jpa.builder;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import lombok.Data;
import wang.uvu.jpa.base.Query;
import wang.uvu.jpa.statement.QueryStatement;
import wang.uvu.jpa.statement.Statement;

import org.springframework.data.domain.PageRequest;


@Data
public class QueryBuilder {
	
	public static QueryStatement build(Root<?> root, CriteriaBuilder criteriaBuilder,Query query) {
		QueryStatement stmt = new QueryStatement();
		List<Selection<?>> fields = FieldBuilder.build(root, query);
		PageRequest page = PageBuilder.build(query);
		Statement statement = StatementBuilder.build(root, criteriaBuilder, query);
		stmt.setFields(fields);
		stmt.setPage(page); 
		stmt.setStatement(statement);
		return stmt;
	}
}
