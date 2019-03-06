package wang.uvu.jpa;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import wang.uvu.jpa.repository.QueryRepositoryImpl;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableJpaRepositories(repositoryBaseClass = QueryRepositoryImpl.class)
public @interface EnableQuery {

}
