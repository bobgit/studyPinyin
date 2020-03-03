package github.com.bobgit.study.pinyin.model;

import github.com.bobgit.study.pinyin.common.BaseJsonObject;
import java.util.Date;

public class CronTable extends BaseJsonObject {
    private Integer id;

    private String userId;

    private String cron;

    private String quarzName;

    private String schedulerClass;

    private Date time;

    public CronTable(Integer id, String userId, String cron, String quarzName, String schedulerClass, Date time) {
        this.id = id;
        this.userId = userId;
        this.cron = cron;
        this.quarzName = quarzName;
        this.schedulerClass = schedulerClass;
        this.time = time;
    }

    public CronTable() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getQuarzName() {
        return quarzName;
    }

    public void setQuarzName(String quarzName) {
        this.quarzName = quarzName;
    }

    public String getSchedulerClass() {
        return schedulerClass;
    }

    public void setSchedulerClass(String schedulerClass) {
        this.schedulerClass = schedulerClass;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}