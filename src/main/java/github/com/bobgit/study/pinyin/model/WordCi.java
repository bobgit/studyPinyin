package github.com.bobgit.study.pinyin.model;

import github.com.bobgit.study.pinyin.common.BaseJsonObject;

public class WordCi extends BaseJsonObject {
    private Integer id;

    private String ci;

    private String explanation;

    private String remark;

    public WordCi(Integer id, String ci, String explanation, String remark) {
        this.id = id;
        this.ci = ci;
        this.explanation = explanation;
        this.remark = remark;
    }

    public WordCi() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}