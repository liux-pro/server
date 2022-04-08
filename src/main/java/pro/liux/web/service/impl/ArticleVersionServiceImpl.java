package pro.liux.web.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import pro.liux.web.mapper.ArticleVersionMapper;
import pro.liux.web.model.ArticleVersion;
import pro.liux.web.model.ArticleVersionExample;import pro.liux.web.service.ArticleVersionService;

@Service
public class ArticleVersionServiceImpl implements ArticleVersionService {

    @Resource
    private ArticleVersionMapper articleVersionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return articleVersionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ArticleVersion record) {
        return articleVersionMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(ArticleVersion record) {
        return articleVersionMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(ArticleVersion record) {
        return articleVersionMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(ArticleVersion record) {
        return articleVersionMapper.insertSelective(record);
    }

    @Override
    public ArticleVersion selectByPrimaryKey(Long id) {
        return articleVersionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ArticleVersion record) {
        return articleVersionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ArticleVersion record) {
        return articleVersionMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<ArticleVersion> list) {
        return articleVersionMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<ArticleVersion> list) {
        return articleVersionMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<ArticleVersion> list) {
        return articleVersionMapper.batchInsert(list);
    }

    @Override
    public long countByExample(ArticleVersionExample example) {
        return articleVersionMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ArticleVersionExample example) {
        return articleVersionMapper.deleteByExample(example);
    }

    @Override
    public List<ArticleVersion> selectByExample(ArticleVersionExample example) {
        return articleVersionMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(ArticleVersion record, ArticleVersionExample example) {
        return articleVersionMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(ArticleVersion record, ArticleVersionExample example) {
        return articleVersionMapper.updateByExample(record, example);
    }
}





