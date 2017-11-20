package com.idogs.laosiji.user.http.vo;

import com.idogs.laosiji.basic.http.YbResponse;
import com.idogs.laosiji.basic.http.YbUser;
import com.idogs.laosiji.basic.http.token.YbToken;

/**
 * Created by y on 2017/8/24.
 */

public class YbLoginResponse extends YbResponse<YbUser> {

    public YbUser user;

    public YbToken token;

    @Override
    public YbUser getResult() {
        return user;
    }
}
