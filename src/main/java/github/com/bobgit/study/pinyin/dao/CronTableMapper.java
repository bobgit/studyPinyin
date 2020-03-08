package github.com.bobgit.study.pinyin.dao;

import github.com.bobgit.study.pinyin.model.CronTable;

public interface CronTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CronTable record);

    int insertSelective(CronTable record);

    CronTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CronTable record);

    int updateByPrimaryKey(CronTable record);
}