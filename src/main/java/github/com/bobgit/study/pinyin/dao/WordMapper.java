package github.com.bobgit.study.pinyin.dao;

import github.com.bobgit.study.pinyin.model.Word;
import github.com.bobgit.study.pinyin.model.WordWithBLOBs;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 区县信息
 */
@Repository
public interface WordMapper {
    // 根据条件查询列表
    List<WordWithBLOBs> listWord(WordRequest params);
    // 根据条件查询列表 总数
    int listWordCount(WordRequest params);

    int deleteByPrimaryKey(Integer id);

    int insert(WordWithBLOBs record);

    int insertSelective(WordWithBLOBs record);

    WordWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WordWithBLOBs record);

    int updateByPrimaryKey(Word record);
}