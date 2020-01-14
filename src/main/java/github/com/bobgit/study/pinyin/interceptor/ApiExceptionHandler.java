package github.com.bobgit.study.pinyin.interceptor;



import github.com.bobgit.study.pinyin.common.JsonResponse;
import github.com.bobgit.study.pinyin.exception.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

@ControllerAdvice  //只能处理 Controller 层未捕获（往外抛）的异常，对于 Interceptor（拦截器）层的异常，Spring 框架层的异常，就无能为力了。
public class ApiExceptionHandler {//配置全局异常捕获器:一方面可以捕获到整个项目中的Exception及其子类（包含RuntimeException等），另一方面可以对异常进行统一处理并返回统一的数据格式，为前端提供友好的数据交互。
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public static JsonResponse errorHandler(NativeWebRequest request, Throwable exception) {
        //logger.info("进入异常监控处理");
//        logger.debug("进入异常监控处理：request {} exception {}", request, exception);
//        logger.warn(exception.getMessage(), exception);
        String errorMsg = "系统错误";
        Integer status = 3000;
        JsonResponse res = new JsonResponse();
        if(exception instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException missingServletRequestParameterException = (MissingServletRequestParameterException) exception;
            status = Constants.ERROR_PARAMETER_EXCEPTION;
            errorMsg = "请求参数错误: " + missingServletRequestParameterException.getMessage();
            logger.debug("请求参数错误: " + exception.getMessage());
            logger.error("请求参数错误: " + missingServletRequestParameterException.getMessage()+" 请求地点:"+request.toString(), exception);
        }
        else if (exception instanceof IllegalArgumentException) {
            IllegalArgumentException illegalArgumentException = (IllegalArgumentException)exception;//例如 NumberFormatException
            if(StringUtils.equalsIgnoreCase("null",illegalArgumentException.getMessage())){
                status = Constants.ERROR_NULL_EXCEPTION;
                errorMsg = "产生null数据异常";//"非法参数异常:" +
                logger.error("非法参数异常:" + exception.getMessage()+" 请求地点:"+request.toString(), exception);
            }
            else{
                errorMsg = illegalArgumentException.getMessage();//"非法参数异常:"+
                logger.error("非法参数异常:" + exception.getMessage()+" 请求地点:"+request.toString(), exception);
            }
        }
        else if (exception instanceof MaxUploadSizeExceededException) {
            MaxUploadSizeExceededException maxUploadSizeExceededException = (MaxUploadSizeExceededException)exception;//例如 NumberFormatException
            logger.info("文件上传大小异常: " + maxUploadSizeExceededException.getMessage(), exception);
            errorMsg = "文件上传大小异常: "+maxUploadSizeExceededException.getLocalizedMessage();
            logger.error("文件上传大小异常: " + maxUploadSizeExceededException.getMessage()+" 请求地点:"+request.toString(), exception);
        }
        else if (exception instanceof ArgumentException) {
            ArgumentException argumentException = (ArgumentException) exception;
            status = argumentException.getCode();
            errorMsg ="参数异常: " + argumentException.getMessage();
            logger.error("参数异常: " + argumentException.getMessage(), argumentException);
        }
        else if (exception instanceof DataAccessException) {
            DataAccessException dataAccessException1 = (DataAccessException)exception;
            logger.info("数据库访问异常: " + dataAccessException1.getMessage(), exception);
            errorMsg = "数据库调用异常: "+dataAccessException1.getLocalizedMessage();
            logger.error("数据库访问异常: " + dataAccessException1.getMessage()+" 请求地点:"+request.toString(), exception);

        }
        else if (exception instanceof TokenException) {
            TokenException tokenException = (TokenException) exception;
            status = tokenException.getCode();
            errorMsg = "token失效: " + tokenException.getMessage();
//            logger.info("token失效: " + exception.getMessage());
//            logger.error("请求参数错误: " + tokenException.getMessage()+" 请求地点:"+request.toString(), exception);
        }
        else if (exception instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException)exception;
            logger.info("Http请求地址无效异常: " + httpClientErrorException.getMessage(), exception);
            errorMsg = "Http请求地址无效: "+httpClientErrorException.getLocalizedMessage();
            logger.error("Http请求地址无效异常: " + httpClientErrorException.getMessage()+" 请求地点:"+request.toString(), exception);

        }
        else if (exception instanceof StatusException) {
            StatusException statusException = (StatusException) exception;
            status = statusException.getCode();
            errorMsg ="状态异常: " + statusException.getMessage();
            if(statusException.getOther()!=null)res.setOtherInfo(statusException.getOther());
            logger.error("状态异常: " + statusException.getMessage(), statusException);
        } else if (exception instanceof ServiceException) {
            ServiceException exp = (ServiceException) exception;
            status = exp.getCode();
            errorMsg = "运行异常: " + exp.getMessage();
            logger.info("业务异常: " + exception.getMessage(), exp);
        }  else if (exception instanceof MonitorException) {
            MonitorException monitorException = (MonitorException) exception;
            status = monitorException.getCode();
            errorMsg = "记录监控异常: " + monitorException.getMessage();
            logger.error("记录监控异常: " + exception.getMessage()+monitorException.other, monitorException);
        } else {
            errorMsg = "系统异常,请联系系统管理员! ("+exception.getMessage()+")";
            status = Constants.RESPONSE_CODE_SERVICE_ERROR;
            logger.error("业务级异常: " + exception.getMessage()+" 请求地点:"+request.toString(), exception);
        }
        res.setStatus(status);
        res.setError(errorMsg);
        return res;

    }

}
