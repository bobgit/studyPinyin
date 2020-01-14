package github.com.bobgit.study.pinyin.util;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import github.com.bobgit.study.pinyin.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    //private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);// LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        ObjectMapper mapper = getObjectMapper();
        try {
            //logger.info(">>>>目前数据 Json:{}",jsonString);
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new ServiceException("Json字符串不能成功转化成有效对象");
        }


    }

    public static <T> T fromJson(String json, TypeReference<T> typereference) {
        logger.debug(">>>>目前数据 Json{}",json);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(json, typereference);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new ServiceException("Json字符串不能转化成有效对象");
        }
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(dateFormat);*/
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);//这种方法的好处是不用改变要转化的类，即本例的YourClass。（如果YourClass不是你维护的，或者不可修改的，可以用这个方法）
        //@JsonIgnoreProperties(ignoreUnknown = true)  jackson库还提供了注解方法，用在class级别上
        return mapper;
    }
    private static ObjectMapper getObjectMapperForString() {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//让默认的log4j 输入输出采用的就是jackson "gmtCreate":1555053486000 ==>"gmtCreate": "2019-04-12 12:18:56",
        mapper.setDateFormat(dateFormat);*/
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);//这种方法的好处是不用改变要转化的类，即本例的YourClass。（如果YourClass不是你维护的，或者不可修改的，可以用这个方法）
        //@JsonIgnoreProperties(ignoreUnknown = true)  jackson库还提供了注解方法，用在class级别上
        return mapper;
    }
    public static String toJsonString(Object obj) {
        ObjectMapper mapper = getObjectMapperForString();

        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            //logger.err
            logger.error(e.getMessage(), e);
        }
        return jsonString;
    }



    public static String toJsonString(Object obj,String pattern) {
        ObjectMapper mapper = new ObjectMapper();
        if(StringUtils.isNotBlank(pattern)){
            DateFormat dateFormat = new SimpleDateFormat(pattern);//让默认的log4j 输入输出采用的就是jackson "gmtCreate":1555053486000 ==>"gmtCreate": "2019-04-12 12:18:56",
            mapper.setDateFormat(dateFormat);
        }
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            //logger.err
            logger.error(e.getMessage(), e);
        }
        return jsonString;
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz,String pattern) {
        ObjectMapper mapper = new ObjectMapper();
        if(StringUtils.isNotBlank(pattern)){
            DateFormat dateFormat = new SimpleDateFormat(pattern);//让默认的log4j 输入输出采用的就是jackson "gmtCreate":1555053486000 ==>"gmtCreate": "2019-04-12 12:18:56",
            mapper.setDateFormat(dateFormat);
        }
        try {
            //logger.info(">>>>目前数据 Json:{}",jsonString);
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new ServiceException("Json字符串不能成功转化成有效对象");
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typereference,String pattern) {
        logger.debug(">>>>目前数据 Json{}",json);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        if(StringUtils.isNotBlank(pattern)){
            DateFormat dateFormat = new SimpleDateFormat(pattern);//让默认的log4j 输入输出采用的就是jackson "gmtCreate":1555053486000 ==>"gmtCreate": "2019-04-12 12:18:56",
            mapper.setDateFormat(dateFormat);
        }
        try {
            return mapper.readValue(json, typereference);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new ServiceException("Json字符串不能转化成有效对象");
        }
    }
    /**
     * 将对象序列化为JSON字符串
     * @param object
     * @return
     */
    public static String serialize(Object object) {
        Writer write = new StringWriter();
        try {
            objectMapper.writeValue(write, object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException when serialize object to json", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException when serialize object to json", e);
        } catch (IOException e) {
            logger.error("IOException when serialize object to json", e);
        }
        return write.toString();
    }

    /**
     * 将JSON字符串反序列化为对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        Object object = null;
        try {
            object = objectMapper.readValue(json, TypeFactory.rawClass(clazz));
        } catch (JsonParseException e) {
            logger.error("JsonParseException when serialize object to json", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException when serialize object to json", e);
        } catch (IOException e) {
            logger.error("IOException when serialize object to json", e);
        }
        return (T) object;
    }

    /**
     * 将JSON字符串反序列化为对象
     * @param json
     * @param typeRef
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, TypeReference<T> typeRef) {
        try {
            return (T) objectMapper.readValue(json, typeRef);
        } catch (JsonParseException e) {
            logger.error("JsonParseException when deserialize json", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException when deserialize json", e);
        } catch (IOException e) {
            logger.error("IOException when deserialize json", e);
        }
        return null;
    }
/*

异常说的值服务器返回了一个带有日期的json，日期的形式是字符串2018-03-07 16:18:35，jackson无法将该字符串转成一个Date对象，网上查资料，上面说的是jackson只支持以下几种日期格式：

"yyyy-MM-dd'T'HH:mm:ss.SSSZ"；
"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"；
"yyyy-MM-dd";
"EEE, dd MMM yyyy HH:mm:ss zzz";
long类型的时间戳
2018-03-07 16:18:35
 */
}
