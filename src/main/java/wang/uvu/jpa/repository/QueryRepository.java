package wang.uvu.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

import wang.uvu.jpa.base.Query;


@NoRepositoryBean
public interface QueryRepository<T> {

	<Q extends Query> Page<T> find(Q query);

	<Q extends Query> long count(Q query);
}
