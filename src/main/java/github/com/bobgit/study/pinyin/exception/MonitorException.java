package github.com.bobgit.study.pinyin.exception;


import github.com.bobgit.study.pinyin.util.IPUtils;

import javax.servlet.http.HttpServletRequest;

public class MonitorException extends CommonException {
    public static final int CODE = Constants.ERROR_MONITOR_RUDE_EXCEPTION;
    public static final String MSG = "状态错误, 客户端需要执行刷新操作";

    public String other = "其他信息";

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public MonitorException(Throwable e) {
        super(CODE, MSG, e);
    }
    public MonitorException(String msg, Throwable e) {
        super(CODE, msg, e);
    }

    public MonitorException(int code, String msg, Throwable e) {
        super(code, msg, e);
    }

    public MonitorException(String msg) {
        super(CODE, msg);
    }

    public MonitorException(int code, String msg) {
        super(code, msg);
    }
    public MonitorException(int code, String msg, String other) {
        super(code, msg);
        this.other = other;
    }
    public MonitorException(int code, String msg, HttpServletRequest request) {
        super(code, msg);
        String getRemoteAddress = IPUtils.getRemoteAddress(request);
        String remoteAddress = IPUtils.getSimpleRemoteAddress(request);
        this.other = "远程访问IP:"+getRemoteAddress+"| 可能的代理IP:"+remoteAddress;
    }
}
