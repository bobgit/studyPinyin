package github.com.bobgit.study.pinyin.dao;

import github.com.bobgit.study.pinyin.model.CronTable;
import org.springframework.stereotype.Repository;

/**
 * 字词信息
 */
@Repository
public interface CronTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CronTable record);

    int insertSelective(CronTable record);

    CronTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CronTable record);

    int updateByPrimaryKey(CronTable record);
}