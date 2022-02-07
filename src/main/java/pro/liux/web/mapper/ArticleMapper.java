package pro.liux.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import pro.liux.web.vo.Article;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article (title, content, gmt_create, gmt_modified) " +
            "VALUES(#{title}, #{content}, #{gmtCreate}, #{gmtModified})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Article article);

    @Select("select * from article where id = #{id}")
    Article selectById(Integer id);
}
