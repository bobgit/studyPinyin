package github.com.bobgit.study.pinyin.model;

import github.com.bobgit.study.pinyin.common.BaseJsonObject;

public class WordIdiom extends BaseJsonObject {
    private Integer id;

    private String abbreviation;

    private String derivation;

    private String example;

    private String explanation;

    private String pinyin;

    private String word;

    private String remark;

    public WordIdiom(Integer id, String abbreviation, String derivation, String example, String explanation, String pinyin, String word, String remark) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.derivation = derivation;
        this.example = example;
        this.explanation = explanation;
        this.pinyin = pinyin;
        this.word = word;
        this.remark = remark;
    }

    public WordIdiom() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDerivation() {
        return derivation;
    }

    public void setDerivation(String derivation) {
        this.derivation = derivation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}