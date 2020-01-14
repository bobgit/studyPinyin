package github.com.bobgit.study.pinyin.model;

import github.com.bobgit.study.pinyin.common.BaseJsonObject;

public class Word extends BaseJsonObject {
    private Integer id;

    private String oldword;

    private String pinyin;

    private String radicals;

    private String strokes;

    private String word;

    private String yin;

    private String syllable;

    private String headlVowel;

    private String tailVowel;

    private String tone;

    public Word(Integer id, String oldword, String pinyin, String radicals, String strokes, String word, String yin, String syllable, String headlVowel, String tailVowel, String tone) {
        this.id = id;
        this.oldword = oldword;
        this.pinyin = pinyin;
        this.radicals = radicals;
        this.strokes = strokes;
        this.word = word;
        this.yin = yin;
        this.syllable = syllable;
        this.headlVowel = headlVowel;
        this.tailVowel = tailVowel;
        this.tone = tone;
    }

    public Word() {
        super();
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

    public String getRadicals() {
        return radicals;
    }

    public void setRadicals(String radicals) {
        this.radicals = radicals;
    }

    public String getStrokes() {
        return strokes;
    }

    public void setStrokes(String strokes) {
        this.strokes = strokes;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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

    public String getHeadlVowel() {
        return headlVowel;
    }

    public void setHeadlVowel(String headlVowel) {
        this.headlVowel = headlVowel;
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