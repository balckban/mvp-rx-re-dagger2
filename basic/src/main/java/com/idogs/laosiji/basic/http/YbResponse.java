package com.idogs.laosiji.basic.http;


/**
 * Created by y on 2017/7/19.
 */

public abstract class YbResponse<T> {
    /**
     * 错误信息
     */
    public String error_mesg;
    /**
     * 应答结果，0为正常，1为异常
     */
    public Integer error;

    /**
     * 获取响应中的结果
     * @return
     */
    public abstract T getResult();

}
