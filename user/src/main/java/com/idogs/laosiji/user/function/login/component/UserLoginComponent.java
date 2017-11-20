package com.idogs.laosiji.user.function.login.component;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.user.function.login.UserLoginActivity;
import com.idogs.laosiji.user.function.login.module.UserLoginModule;

import dagger.Component;

/**
 * Created by y on 2017/8/15.
 */
@ActivityScope
@Component(modules = UserLoginModule.class, dependencies = YbBasicComponent.class)
public interface UserLoginComponent {
    void inject(UserLoginActivity userLoginActivity);

}
