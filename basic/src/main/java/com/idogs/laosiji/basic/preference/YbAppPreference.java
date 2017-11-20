package com.idogs.laosiji.basic.preference;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.idogs.laosiji.basic.http.YbUser;
import com.idogs.laosiji.basic.http.token.YbToken;
import com.idogs.laosiji.core.preference.AppPreference;

/**
 * Created by y on 2017/8/15.
 */

public class YbAppPreference extends AppPreference {

    private SPUtils spUtils;

    Gson gson = new Gson();


    public YbAppPreference(SPUtils spUtils) {
        super(spUtils);
        this.spUtils = spUtils;
    }

    /**
     * 保存TOKEN
     * @param token
     */
    public void saveToken(YbToken token){
        String tokenStr = gson.toJson(token);
        put("token",tokenStr);
    }

    /**
     * 获取TOKEN
     * @return
     */
    public YbToken getToken(){
        String tokenStr = getString("token");
        if(!StringUtils.isEmpty(tokenStr))
            return gson.fromJson(tokenStr,YbToken.class);
        return null;
    }

    /**
     * 获取TOKEN
     * @return
     */
    public String getTokenKey(){
        YbToken token = getToken();
        if(token != null)
            return token.key;
        return "";
    }

    /**
     * 保存用户
     * @param user
     */
    public void saveUser(YbUser user){
        String userStr = gson.toJson(user);
        put("user",userStr);
    }

    /**
     * 获取本地用户信息
     * @return
     */
    public YbUser getUser(){
        String userStr = getString("user");
        if(!StringUtils.isEmpty(userStr))
            return gson.fromJson(userStr,YbUser.class);
        return null;
    }
}
