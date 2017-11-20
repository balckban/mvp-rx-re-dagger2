package com.idogs.laosiji.user.http.vo;


import com.idogs.laosiji.basic.http.YbResponse;

/**
 * 发短信前的校验码
 * Created by y on 2017/7/28.
 */

public class YbVcodeResponse extends YbResponse<String> {

    public String vcode;

    @Override
    public String getResult() {
        return vcode;
    }
}
