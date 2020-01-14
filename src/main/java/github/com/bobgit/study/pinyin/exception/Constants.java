package github.com.bobgit.study.pinyin.exception;

public interface Constants {
    /** 成功 */
    int RESPONSE_CODE_SUCCESS = 0;

    /** 参数错误 */
    int RESPONSE_CODE_PARAMETER_ERROR = 1000;
    /** 无效参数错误 */
    int ERROR_INVALID_PARAMETER = 1001;
    /** 没有partner 或 channel */
    int RESPONSE_CODE_PARAMETER_INVALID_PARTNER_ID = 1010;

    /** 签名错误 */
    int RESPONSE_CODE_PARAMETER_INVALID_SIGN = 1011;

    /** 应用业务逻辑错误 */
    int RESPONSE_CODE_APPLICATION_ERROR = 2000;

    /** 业务异常 **/
    int USER_EXIST_ERROR = 2001;
    int USER_NOT_EXIST_ERROR = 2002;

    /** 重复数据错误. 在插入数据时, 如果有相同的数据存在, 会返回这个错误 */
    int RESPONSE_CODE_DUPLICATE_ERROR = 2010;


    /**
     *收到2003错误码强制刷新
     */
    int TASK_PROCESS_ERROR = 2003;

    /** 版本号太低 强制升级**/
    int RESPONSE_CODE_VERSION_ERROR = 2100;
    /** 版本号太低 建议升级**/
    int RESPONSE_CODE_VERSION_WARN = 2110;
    /** 版本号太低 建议升级**/
    int RESPONSE_CODE_VERSION_INFO= 2111;

    /** 没有数据 */
    int RESPONSE_CODE_NO_DATA = 2200;

    /** 状态错误, 客户端需要执行刷新操作 */
    int RESPONSE_CODE_STATUS_ERROR_WITH_REFRESH = 2300;
    int RESPONSE_CODE_STATUS_ERROR_NO_REFRESH = 2301;

    /**权限错误*/
    int RESPONSE_CODE_PRIVILEGE_ERROR = 2400;

    /** 服务系统错误 */
    int RESPONSE_CODE_SERVICE_ERROR = 3000;
    /** 服务系统错误友好提醒 */
    int RESPONSE_CODE_FRIEND_ERROR = 3666;

    /** token失效 */
    int RESPONSE_CODE_TOKEN_ERROR = 4000;
    /** 请求取消 */
    int ERROR_CANCEL = 4001;
    /** 远程调用请求错误 */
    int ERROR_REMOTE_ERROR = 4011;
    /** 产生出现空指针错误异常 */
    int ERROR_NULL_EXCEPTION = 4020;
    /** 粗鲁登录异常 */
    int ERROR_MONITOR_RUDE_EXCEPTION = 4100;
    /** 未知错误 */
    int ERROR_UNKNOW = 9999;

    int RESPONSE_CODE_NOT_CORRESPONDED_VALETCOUPON_ERROR = 5000;
    int HTTP_CACHE = 5 * 60; // 5分钟

    int ERROR_SERVICE_EXCEPTION = 10000;//10000	服务内部错误	服务端内部逻辑错误,请稍后重试	500
    int ERROR_RESPONSE_OUTTIME = 10001;//10001	服务响应超时	内部服务响应超时	504
    int ERROR_SECRET_EXCEPTION = 10002;//10002	Secret错误	Access Key与 Access Secret 不匹配	401
    int ERROR_SIGNATURE_EXCEPTION = 10003;//10003	验证签名错误	验证签名错误	401
    int ERROR_PARAMETER_EXCEPTION = 10004;//10004	参数错误	参数错误	400
    int ERROR_CODE_ISNOTEXIST = 20001;//20001	处方编号不存在	没有找到匹配的处方编号	200

    int ERROR_USERWALLET_NO_SET = 10020;//10020	用户钱包没有设定
    int ERROR_RETRY_LOGIN = 10040;//10040	用户重试登录密码


    int ERROR_STATE_1_EXCEPTION = 11001;//该用户状态是删除
    int ERROR_STATE_2_EXCEPTION = 11002;//该用户状态是停用.
    int ERROR_STATE_3_EXCEPTION = 11003;//该用户状态是冻结
    int ERROR_STATE_4_EXCEPTION = 11004;//审核中，请等待...
    int ERROR_STATE_5_EXCEPTION = 11005;//审核未通过

    int RESPONSE_OPEN_FEIGN_ERROR = 8402;
}
