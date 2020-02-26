package github.com.bobgit.study.pinyin.model;

import github.com.bobgit.study.pinyin.common.BaseJsonObject;

public class WordXhy extends BaseJsonObject {
    private Integer id;

    private String answer;

    private String riddle;

    private String remark;

    public WordXhy(Integer id, String answer, String riddle, String remark) {
        this.id = id;
        this.answer = answer;
        this.riddle = riddle;
        this.remark = remark;
    }

    public WordXhy() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}