package com.idogs.laosiji.user.function.login.presenter;

import com.idogs.laosiji.basic.component.YbNetworkComponent;
import com.idogs.laosiji.basic.ext.YbPresenterImpl;
import com.idogs.laosiji.basic.http.YbUser;
import com.idogs.laosiji.basic.http.covert.YbLocalResponseTransformer;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.basic.util.TipUtil;
import com.idogs.laosiji.basic.util.Validator;
import com.idogs.laosiji.user.function.login.contract.UserLoginContract;
import com.idogs.laosiji.user.http.component.DaggerUserHttpComponent;
import com.idogs.laosiji.user.http.module.UserHttpModule;
import com.idogs.laosiji.user.http.server.UserServer;
import com.idogs.laosiji.user.http.vo.YbLoginResponse;
import com.idogs.laosiji.user.http.vo.YbVcodeResponse;
import com.idogs.laosiji.user.http.vo.YbYzCodeResponse;

import javax.inject.Inject;


/**
 * Created by y on 2017/8/15.
 */

public class UserLoginPresenterImpl extends YbPresenterImpl<UserLoginContract.View> implements UserLoginContract.Presenter {

    @Inject
    UserServer userServer;

    @Inject
    YbAppPreference appPreference;

    YbLoginParam param;

    public UserLoginPresenterImpl(UserLoginContract.View view) {
        super(view);
        param = new YbLoginParam();
    }

    @Override
    public void injectNetComponet2(YbNetworkComponent component) {
        DaggerUserHttpComponent.builder().ybNetworkComponent(component).userHttpModule(new UserHttpModule()).build().inject(this);
    }


    @Override
    public boolean validatePhone(String phone) {
        String res = Validator.checkPhone(phone);
        //1.1 手机号码格式有误
        if (res != null) {
            view.toast(res);
            return false;
        }
        return true;
    }

    @Override
    public boolean validateYzcode(String yzcode) {
        String res = Validator.checkYzCode(yzcode);
        //1.1 验证码码格式有误
        if (res != null) {
            view.toast(res);
            return false;
        }
        return true;
    }

    @Override
    public void login(String phone, String yzcode) {
        //设置参数
        param.yzcode = yzcode;
        if(validatePhone(param.phone) && validateYzcode(param.yzcode)){
            userServer.login(param.phone,param.yzcode,param.sid,param.type,"","","ANDROID")

                    .compose(new YbLocalResponseTransformer<YbLoginResponse, YbUser>())
                    .doOnSubscribe(disposable -> {view.setRequestTag("login",disposable);view.showWait();})
                    .subscribe(user->{
                        appPreference.saveUser(user);
                        view.closeWait();
                    },throwable -> {});
        }
    }

    @Override
    public void sendYzcode(String phone) {
        //设置参数
        param.phone = phone;
        if(validatePhone(param.phone) && view.countingDown(param.countSeconds)){
            userServer.gethash(phone,param.cType)
                    //获取校验码
                    .compose(new YbLocalResponseTransformer<YbVcodeResponse, String>())
                    .flatMap(string -> { return userServer.getsms(param.phone,string,param.type,param.cType);})
                    //获取验证码
                    .compose(new YbLocalResponseTransformer<YbYzCodeResponse, String >())
                    .doOnSubscribe(disposable -> {view.setRequestTag("sendYzcode",disposable);})
                    .subscribe(sid->{param.sid = sid;view.toast(TipUtil.sendSmsSuccess);},throwable -> {
                        view.cancelRequest("sendYzcode");
                        param.error_mesg = throwable instanceof java.net.ConnectException ? TipUtil.netWorkError:throwable.getMessage();
                        //停止计时
                        view.countEnd(param.error_mesg);
                    });

        }
    }

    @Override
    public void wxLogin() {
        //TODO 判断参数正确性
        userServer.login(param.phone,param.yzcode,param.sid,param.type,"","android","android")
        .compose(new YbLocalResponseTransformer<YbLoginResponse, YbUser>())
        .doOnSubscribe(disposable -> {view.showWait();view.setRequestTag("login-user",disposable);})
        .subscribe(ybUser -> {view.hideWait(null);},throwable -> {});
    }

}
