package github.com.bobgit.study.pinyin.model;

public class WordWithBLOBs extends Word {
    private String explanation;

    private String more;

    public WordWithBLOBs(Integer id, String oldword, String pinyin, String radicals, String strokes, String word, String yin, String syllable, String headlVowel, String tailVowel, String tone, String explanation, String more) {
        super(id, oldword, pinyin, radicals, strokes, word, yin, syllable, headlVowel, tailVowel, tone);
        this.explanation = explanation;
        this.more = more;
    }

    public WordWithBLOBs() {
        super();
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }
}