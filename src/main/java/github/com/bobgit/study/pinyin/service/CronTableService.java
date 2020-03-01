package github.com.bobgit.study.pinyin.service;

import github.com.bobgit.study.pinyin.dao.CronTableMapper;
import github.com.bobgit.study.pinyin.model.CronTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CronTableService {
    private final Logger log = LoggerFactory.getLogger(CronTableService.class);
    @Autowired
    private CronTableMapper cronTableMapper;

    public void sendSms(String quartzName) {
        System.out.println(quartzName +"正在发送短信");
    }
    public void sendEmail(String quartzName) {
        System.out.println(quartzName +" 正在发送邮件");
    }

    public int deleteByPrimaryKey(Integer id){
        return cronTableMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(CronTable record){
        return cronTableMapper.insertSelective(record);
    }

    public CronTable selectByPrimaryKey(Integer id){
        return cronTableMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CronTable record){
        return cronTableMapper.updateByPrimaryKeySelective(record);
    }
}
