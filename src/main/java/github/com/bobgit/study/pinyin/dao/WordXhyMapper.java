package github.com.bobgit.study.pinyin.dao;

import github.com.bobgit.study.pinyin.model.WordXhy;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 字词信息
 */
@Repository
public interface WordXhyMapper {
    // 根据条件查询列表
    List<WordXhy> listWordXhy(WordRequest params);
    // 根据条件查询列表 总数
    int listWordXhyCount(WordRequest params);

    int deleteByPrimaryKey(Integer id);

    int insert(WordXhy record);

    int insertSelective(WordXhy record);

    WordXhy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordXhy record);

    int updateByPrimaryKey(WordXhy record);
}