package com.idogs.laosiji.user.function.login;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.user.R;
import com.idogs.laosiji.user.R2;
import com.idogs.laosiji.user.function.login.component.DaggerUserLoginComponent;
import com.idogs.laosiji.user.function.login.contract.UserLoginContract;
import com.idogs.laosiji.user.function.login.module.UserLoginModule;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by y on 2017/8/15.
 */
@Route(path = RouterConfig.USER_LOGIN)
public class UserLoginActivity extends YbAbstractActivity<UserLoginContract.Presenter> implements UserLoginContract.View {


    /**
     * 发送验证码按钮
     */
    @BindView(R2.id.bt_phone)
    Button mSendTextBt;

    /**
     * 手机号码编辑框
     */
    @BindView(R2.id.et_phone)
    EditText mPhoneEt;

    /**
     * 验证码编辑框
     */
    @BindView(R2.id.et_code)
    EditText mYzcodeEt;

    /**
     * 登录按钮
     */
    @BindView(R2.id.bt_login)
    Button mLoginBtn;

    /**
     * 微信登录图标（可以在xml文件里将图标和文字放到一个layout里面，然后点击layout）
     */
    @BindView(R2.id.iv_wechat)
    ImageView mWeChatLogin;

    @Inject
    UserLoginContract.Presenter presenter;

    /**
     * 倒计时被观察对象
     */
    Observable<Long> observable;
    /**
     * 倒计时观察者
     */
    Observer<Long> observer;

    /**
     * 倒计时标签
     */
    String countTag = "user_login_countingdown";

    @Override
    public int bindLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    public void afterViews() {
        //1.发送短信事件绑定
        RxView.clicks(mSendTextBt) //防止重复点击
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(
                        obj->{
                            String phone = mPhoneEt.getText().toString().trim();
                            presenter.sendYzcode(phone);
                        },throwable -> {
                            toast(throwable.getMessage());
                        });
        //2.登录事件绑定
        RxView.clicks(mLoginBtn) //防止重复点击
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(
                        obj->{
                            String phone = mPhoneEt.getText().toString().trim();
                            String yzcode = mYzcodeEt.getText().toString().trim();
                            presenter.login(phone,yzcode);
                        },throwable -> {
                            toast(throwable.getMessage());
                        });

    }

    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {
        DaggerUserLoginComponent.builder().ybBasicComponent(basicComponent).userLoginModule(new UserLoginModule(this)).build().inject(this);

    }


    /**
     * 启动倒计时
     *
     * @param seconds
     * @return
     */
    @Override
    public boolean countingDown(int seconds) {

        try { //防止重复点击
            RxView.enabled(mSendTextBt).accept(false);
            //倒数秒数不合法
            if (seconds < 1) {
                RxView.enabled(mSendTextBt).accept(true);
                toast(getResources().getString(R.string.user_send_mesg_second_invalid));
                return false;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //使用observable的interval发送定时信号
        observable = Observable.interval(1, TimeUnit.SECONDS, Schedulers.io()).take(seconds)
                //将递增数字替换成递减的倒计时数字
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return seconds - (aLong + 1);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());//切换到 Android 的主线程。
        observer= new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                setRequestTag(countTag, d);
            }

            @Override
            public void onNext(Long aLong) {
                try {
                    if (aLong == 0) {
                        RxView.enabled(mSendTextBt).accept(true);
                        RxTextView.text(mSendTextBt).accept("重新发送");
                    } else {
                        RxTextView.text(mSendTextBt).accept("(" + aLong + "秒)");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                toast(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
        return true;
    }

    /**
     * 倒计时终止
     * @return
     */
    @Override
    public boolean countEnd(String errorMesg) {
        if(!StringUtils.isEmpty(errorMesg)) {
            toast(errorMesg);
            try {
                cancelRequest(countTag);
                RxView.enabled(mSendTextBt).accept(true);
                RxTextView.text(mSendTextBt).accept("重新发送");
            } catch (Exception e1) {
                toast(e1.getMessage());
            }
            return true;
        }
        return false;
    }
}
