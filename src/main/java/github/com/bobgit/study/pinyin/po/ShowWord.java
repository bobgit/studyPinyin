package github.com.bobgit.study.pinyin.po;

import github.com.bobgit.study.pinyin.common.BaseJsonObject;
import github.com.bobgit.study.pinyin.model.WordWithBLOBs;

public class ShowWord extends BaseJsonObject {
    private Integer id;

    private String oldword;

    private String pinyin;

    private String yin;

    private String syllable;

    private String tailVowel;

    private String tone;

    public ShowWord(){}
    public ShowWord(WordWithBLOBs word){
        this.id = word.getId();
        this.oldword = word.getOldword();
        this.pinyin = word.getPinyin();
        this.yin = word.getYin();
        this.syllable = word.getSyllable();
        this.tailVowel = word.getTailVowel();
        this.tone = word.getTone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldword() {
        return oldword;
    }

    public void setOldword(String oldword) {
        this.oldword = oldword;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getYin() {
        return yin;
    }

    public void setYin(String yin) {
        this.yin = yin;
    }

    public String getSyllable() {
        return syllable;
    }

    public void setSyllable(String syllable) {
        this.syllable = syllable;
    }

    public String getTailVowel() {
        return tailVowel;
    }

    public void setTailVowel(String tailVowel) {
        this.tailVowel = tailVowel;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }
}
