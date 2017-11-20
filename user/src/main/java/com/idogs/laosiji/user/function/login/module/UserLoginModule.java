package com.idogs.laosiji.user.function.login.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.user.function.login.contract.UserLoginContract;
import com.idogs.laosiji.user.function.login.presenter.UserLoginPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by y on 2017/8/15.
 */
@Module
public class UserLoginModule {

    private UserLoginContract.View view;

    public UserLoginModule(UserLoginContract.View view){this.view = view;}

    @ActivityScope
    @Provides
    UserLoginContract.View providerView() {
        return view;
    }


    @ActivityScope
    @Provides
    UserLoginContract.Presenter providerPresenter(UserLoginContract.View view){
        return new UserLoginPresenterImpl(view);
    }
}
