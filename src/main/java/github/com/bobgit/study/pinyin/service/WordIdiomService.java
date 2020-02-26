package github.com.bobgit.study.pinyin.service;

import github.com.bobgit.study.pinyin.dao.WordIdiomMapper;
import github.com.bobgit.study.pinyin.model.WordIdiom;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordIdiomService {
    private final Logger log = LoggerFactory.getLogger(WordIdiomService.class);
    @Autowired
    private WordIdiomMapper wordIdiomMapper;

    // 根据条件查询列表
    public List<WordIdiom> listWordIdiom(WordRequest params){
        return wordIdiomMapper.listWordIdiom(params);
    }
    // 根据条件查询列表 总数
    public int listWordIdiomCount(WordRequest params){
        return wordIdiomMapper.listWordIdiomCount(params);
    }
}
