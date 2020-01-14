package github.com.bobgit.study.pinyin.service;

import github.com.bobgit.study.pinyin.dao.WordMapper;
import github.com.bobgit.study.pinyin.model.Word;
import github.com.bobgit.study.pinyin.model.WordWithBLOBs;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    private final Logger logger = LoggerFactory.getLogger(WordService.class);
    @Autowired
    private WordMapper wordMapper;

    // 根据条件查询列表
    public List<Word> listWord(WordRequest params){
        return wordMapper.listWord(params);
    }
    // 根据条件查询列表 总数
    public int listWordCount(WordRequest params){
        return wordMapper.listWordCount(params);
    }

    public int deleteByPrimaryKey(Integer id){
        return wordMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(WordWithBLOBs record){
        return wordMapper.insertSelective(record);
    }

    public WordWithBLOBs selectByPrimaryKey(Integer id){
        return wordMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WordWithBLOBs record){
        return wordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(WordWithBLOBs record){
        return wordMapper.updateByPrimaryKeyWithBLOBs(record);
    }
}
