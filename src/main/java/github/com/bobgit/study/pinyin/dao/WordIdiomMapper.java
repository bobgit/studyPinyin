package github.com.bobgit.study.pinyin.dao;

import github.com.bobgit.study.pinyin.model.WordIdiom;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 字词信息
 */
@Repository
public interface WordIdiomMapper {
    // 根据条件查询列表
    List<WordIdiom> listWordIdiom(WordRequest params);
    // 根据条件查询列表 总数
    int listWordIdiomCount(WordRequest params);

    int deleteByPrimaryKey(Integer id);

    int insert(WordIdiom record);

    int insertSelective(WordIdiom record);

    WordIdiom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordIdiom record);

    int updateByPrimaryKey(WordIdiom record);
}