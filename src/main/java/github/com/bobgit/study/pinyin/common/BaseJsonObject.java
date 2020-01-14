package github.com.bobgit.study.pinyin.common;


//import io.swagger.annotations.ApiModelProperty;

import github.com.bobgit.study.pinyin.util.JsonUtils;

import java.io.Serializable;

public abstract class BaseJsonObject implements Serializable {
    private static final long serialVersionUID = 7255183217689124288L;
//    @ApiModelProperty(value = "加密秘钥传输")
    private String encryption;
    public String getEncryption() {
        return encryption;
    }
    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }
    public String toJsonString() {
        //BaseJsonObject 界定toString() ,logger 输出对象的话,对应处理,用了 JsonUtils.toJsonString()方法 .
        return JsonUtils.toJsonString(this);
/*        String ret = "";
        ObjectMapper om = new ObjectMapper();
        try {
            ret = om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LoggerFactory.getLogger(BaseJsonObject.class).trace(e.getMessage(), e);

        }
        return ret;*/
    }


    @Override
    public String toString() {
        return this.toJsonString();
    }

}
