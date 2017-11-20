package com.idogs.laosiji.user.function.login.contract;

import com.idogs.laosiji.basic.ext.YbView;
import com.idogs.laosiji.core.base.BasePresenter;

/**
 * Created by y on 2017/8/15.
 */

public interface UserLoginContract {

    interface View extends YbView {

        /**
         * 倒计时
         * @param seconds
         * @return
         */
        boolean countingDown(int seconds);

        /**
         * 倒计时结束
         */
        boolean countEnd(String errorMe);

    }

    interface Presenter extends BasePresenter {


        /**
         * 验证手机号码
         * @param phone
         * @return
         */
        boolean validatePhone(String phone);

        /**
         * 验证验证码
         * @param yzcode
         * @return
         */
        boolean validateYzcode(String yzcode);
        /**
         * 登录事件的
         * @param phone
         * @param yzcode
         */
        void login(String phone,String yzcode);

        /**
         * 发送验证码
         * @param phone
         */
        void sendYzcode(String phone);

        /**
         * 微信登录
         */
        void wxLogin();

    }
}
