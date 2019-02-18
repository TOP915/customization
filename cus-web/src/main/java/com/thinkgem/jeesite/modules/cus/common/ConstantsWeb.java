package com.thinkgem.jeesite.modules.cus.common;

/**
 * @ClassName: ConstantsWeb
 * @Description: web常量
 * @author: dengyn
 * @date: 2018/8/22 10:33
 */
public class ConstantsWeb {


    public final static String OPT_CODE = "OPT_CODE";  //操作码key值
    public final static String OPT_INFO = "OPT_INFO";  //操作信息key值
    public final static String SESSION_USER_INFO = "CUS_USER";  //操作信息key值
    public final static String PAGE_RESULT_MAP = "RESULT_MAP";  //分页结果返回JSP时MAP key值
    public final static String PAGE_INFO = "PAGE_INFO";  //分页查询结果PAGE_INFO key值
    public final static String PAGE_NUM = "PAGE_NUM";//当前页
    public final static String PAGE_SIZE = "PAGE_SIZE";//分页大小

    public final static int SUCCESS = 915;  //成功
    public final static int UNKNOWN_ERROR = 1;    //	未知错误
    public final static int SERVICE_TEMPORARILY_UNAVAILABLE = 2;    //	服务暂不可用
    public final static int UNSUPPORTED_OPENAPI_METHOD = 3;    //	未知的方法
    public final static int OPEN_API_REQUEST_LIMIT_REACHED = 4;    //	接口调用次数已达到设定的上限
    public final static int UNAUTHORIZED_CLIENT_IP_ADDRESS = 5;    //	请求来自未经授权的IP地址
    public final static int NO_PERMISSION_TO_ACCESS_USER_DATA = 6;    //	无权限访问该用户数据
    public final static int NO_PERMISSION = 7;    //	来自该refer的请求无访问权限
    public final static int INVALID_PARAMETER = 100;    //	请求参数无效
    public final static int INVALID_API_KEY = 101;    //	API key无效
    public final static int INCORRECT_SIGNATURE = 104;    //	无效签名
    public final static int TOO_MANY_PARAMETERS = 105;    //	请求参数过多
    public final static int UNSUPPORTED_SIGNATURE_METHOD = 106;    //	未知的签名方法
    public final static int INVALID_USED_TIMESTAMP_PARAMETER = 107;    //	timestamp参数无效
    public final static int INVALID_USER_INFO_FIELD = 109;    //	无效的用户资料字段名
    public final static int ACCESS_TOKEN_INVALID_OR_NO_LONGER_VALID = 110;    //	无效的access token
    public final static int ACCESS_TOKEN_EXPIRED = 111;    //	access token过期
    public final static int USER_NOT_VISIBLE = 210;    //	用户不可见
    public final static int UNSUPPORTED_PERMISSION = 211;    //	获取未授权的字段
    public final static int NO_PERMISSION_TO_ACCESS_USER_EMAIL = 212;    //	没有权限获取用户的email
    public final static int UNKNOWN_DATA_STORE_API_ERROR = 800;    //	未知的存储操作错误
    public final static int INVALID_OPERATION = 801;    //	无效的操作方法
    public final static int DATA_STORE_ALLOWABLE_QUOTA_WAS_EXCEEDED = 802;    //	数据存储空间已超过设定的上限
    public final static int SPECIFIED_OBJECT_CANNOT_BE_FOUND = 803;    //	指定的对象不存在
    public final static int SPECIFIED_OBJECT_ALREADY_EXISTS = 804;    //	指定的对象已存在
    public final static int A_DATABASE_ERROR_OCCURRED_PLEASE_TRY_AGAIN = 805;    //	数据库操作出错，请重试
    public final static int NO_SUCH_APPLICATION_EXISTS = 900;    //	访问的应用不存在

    public static final String PASSWORD_SUFFIX = "Struggle";

}
