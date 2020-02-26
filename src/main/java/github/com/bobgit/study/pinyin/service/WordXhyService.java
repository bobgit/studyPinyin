package github.com.bobgit.study.pinyin.service;

import github.com.bobgit.study.pinyin.dao.WordXhyMapper;
import github.com.bobgit.study.pinyin.model.WordXhy;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordXhyService {
    private final Logger log = LoggerFactory.getLogger(WordXhyService.class);
    @Autowired
    private WordXhyMapper wordXhyMapper;


    // 根据条件查询列表
    public List<WordXhy> listWordXhy(WordRequest params){
        return wordXhyMapper.listWordXhy(params);
    }
    // 根据条件查询列表 总数
    public int listWordXhyCount(WordRequest params){
        return wordXhyMapper.listWordXhyCount(params);
    }
}
