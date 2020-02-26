package github.com.bobgit.study.pinyin.dao;

import github.com.bobgit.study.pinyin.model.WordCi;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 字词信息
 */
@Repository
public interface WordCiMapper {
    // 根据条件查询列表
    List<WordCi> listWordCi(WordRequest params);
    // 根据条件查询列表 总数
    int listWordCiCount(WordRequest params);

    int deleteByPrimaryKey(Integer id);

    int insert(WordCi record);

    int insertSelective(WordCi record);

    WordCi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordCi record);

    int updateByPrimaryKey(WordCi record);
}