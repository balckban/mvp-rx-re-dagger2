package com.idogs.laosiji.user.function.login.presenter;

/**
 * Created by y on 2017/8/16.
 */

public class YbLoginParam {
    /**
     * 倒数时间秒数
     */
    public int countSeconds = 60;
    /**
     * 原始字符串
      */
    public String rawString;
    /**
     * 类型字段，暂无意义
     */
    public int cType = 1;
    /**
     * 短信类型
     */
    public int type = 1;
    /**
     * 发送短信序列码
     */
    public String sid;
    /**
     * 校验码
     */
    public String vcode;
    /**
     * 手机号码
     */
    public String phone;
    /**
     * 验证码
     */
    public String yzcode;

    /**
     * 异常信息
     */
    public String error_mesg;

    /**
     * 重置
     */
    public void reset(){
        countSeconds = 60;
        rawString = null;
        error_mesg = null;
    }
}
