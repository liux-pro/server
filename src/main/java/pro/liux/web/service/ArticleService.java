package pro.liux.web.service;

import org.springframework.stereotype.Service;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;

import javax.annotation.Resource;

@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    public int deleteByPrimaryKey(Long id) {
        return articleMapper.deleteByPrimaryKey(id);
    }


    public int insert(Article record) {
        return articleMapper.insert(record);
    }


    public int insertSelective(Article record) {
        return articleMapper.insertSelective(record);
    }


    public Article selectByPrimaryKey(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Article record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Article record) {
        return articleMapper.updateByPrimaryKey(record);
    }

}
