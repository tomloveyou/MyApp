package com.yl.myapp.ui.mvp.contract;

import com.standards.library.base.ILoadingView;

public interface UserContract {
    interface UserAddView extends ILoadingView{
        void loginSuccess();
    }
    interface UserLoginView extends ILoadingView{
        void loginSuccess();
    }
    interface UserRegisterView extends ILoadingView{
        void registerSuccess();
    }
    interface UseUpdateView extends ILoadingView{
        void registerSuccess();
    }
}
