package pro.liux.web.mapper;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.liux.web.model.Article;

@Repository
public interface ArticleRedisRepository extends CrudRepository<Article,Long> {

}
