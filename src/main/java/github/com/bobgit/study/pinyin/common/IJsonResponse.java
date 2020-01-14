package github.com.bobgit.study.pinyin.common;

public interface IJsonResponse {
    int getStatus();

    void setStatus(int status);

    String getError();

    void setError(String error);
}
