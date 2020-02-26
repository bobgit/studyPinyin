package github.com.bobgit.study.pinyin.service;

import github.com.bobgit.study.pinyin.dao.WordCiMapper;
import github.com.bobgit.study.pinyin.model.WordCi;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordCiService {
    private final Logger log = LoggerFactory.getLogger(WordCiService.class);
    @Autowired
    private WordCiMapper wordCiMapper;
    // 根据条件查询列表
    public List<WordCi> listWordCi(WordRequest params){
        return wordCiMapper.listWordCi(params);
    }
    // 根据条件查询列表 总数
    public int listWordCiCount(WordRequest params){
        return wordCiMapper.listWordCiCount(params);
    }
}
