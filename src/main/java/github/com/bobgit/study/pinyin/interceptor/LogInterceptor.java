/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package github.com.bobgit.study.pinyin.interceptor;

import github.com.bobgit.study.pinyin.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;


/**
 * 日志拦截器
 *  controller 先执行,
 */
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();// 1、开始时间
        startTimeThreadLocal.set(beginTime);        // 线程绑定变量（该数据只有当前请求的线程可见）
        if (logger.isDebugEnabled()) {
            logger.debug("日志记录拦截器调试打开");
        } else {
            logger.debug("日志记录拦截器调试关闭");
        }
        logger.info("[在执行controller处理之前执行]日志记录拦截器开始计时: {}  URI: {}", new SimpleDateFormat("YYYY-MM-dd hh:mm:ss.SSS").format(beginTime), request.getRequestURI());
        //此时如果返回false 将会停止访问Url
        //logger.info("commonCfg.isDebug():{}",commonCfg.isDebug());//拦截器无法注入，目前是普通类不是service的原因
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = request.getRemoteHost() + ":" + request.getServerPort()+request.getServletPath();
//        logger.info("[在执行controller的处理后，在ModelAndView处理前执行]访问路径是:{}  modelAndView:{}",url,modelAndView);
        if (modelAndView != null) {
            ModelMap modelMap = modelAndView.getModelMap();
//            logger.info("modelMap:{}  model:{}",modelMap,modelAndView.getModel());
            StringBuilder sb = new StringBuilder();
            sb.append("客户IP:").append(IPUtils.getIpAddress(request));
            sb.append("   ");
            if(modelMap.containsAttribute("path")){
                sb.append(" 访问主观路径:").append(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ modelMap.get("path"));
            }
            if(modelMap.containsAttribute("status")){
                sb.append(" 状态码:").append(modelMap.get("status"));
            }
            if(modelMap.containsAttribute("error")){
                sb.append(" 原因:").append(modelMap.get("error"));
            }
            if(modelMap.containsAttribute("message")){
                sb.append(" 消息:").append(modelMap.get("message"));
            }
//            logger.info("视图情况: " + modelAndView.getViewName() + " <<<<<<<<< " + request.getRequestURI() + " >>>>>>>>>  消息:"+sb.toString());
            logger.info(sb.toString());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();    // 2、结束时间
//        long executeTime = endTime - beginTime;    // 3、获取执行时间
        startTimeThreadLocal.remove(); // 用完之后销毁线程变量数据
        logger.info("[在DispatchServlet执行完ModelAndView之后执行]服务地址：{}",request.getRemoteHost() + ":" + request.getServerPort()+request.getServletPath());

/*        String serverAddress = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String remoteAddress = IPUtils.getIpAddress(request);

        Map paramsMap = request.getParameterMap();
        String methodName = request.getMethod();
        logger.info("[处理后]服务地址：{} - 远程访问地址:{}- - 方法类型:{} ",serverAddress, remoteAddress, methodName);*/
        // 保存日志
        //LogUtils.saveLog(UserUtils.getUser(), request, handler, ex, null, null, executeTime);
    }


}
