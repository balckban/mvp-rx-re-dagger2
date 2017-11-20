package com.idogs.laosiji.user.http.vo;


import com.idogs.laosiji.basic.http.YbResponse;

/**
 * Created by y on 2017/7/28.
 */

public class YbYzCodeResponse extends YbResponse<String> {

    /**
     * 验证码序号
     */
    public String sid;

    @Override
    public String getResult() {
        return sid;
    }
}
