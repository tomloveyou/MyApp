package com.yl.myapp.ui.mvp.presenter;

import android.app.Activity;
import android.app.Service;

import com.standards.library.base.BasePresenter;
import com.standards.library.rx.ErrorThrowable;
import com.yl.myapp.api.DataManager;

import com.standards.library.base.ILoadingView;
import com.yl.myapp.bean.UserinfoBean;
import com.yl.myapp.ui.mvp.contract.UserContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import okhttp3.RequestBody;

public class UserPresenterImpl extends BasePresenter<UserContract.UserLoginView> {
    public UserPresenterImpl(Activity activity) {
        super(activity);
    }

    public void login(String userAcount, String password) {
        UserinfoBean bmobUser = new UserinfoBean();
        bmobUser.setUsername(userAcount);
        bmobUser.setPassword(password);
        bmobUser.login(new SaveListener<UserinfoBean>() {
            @Override
            public void done(UserinfoBean userinfoBean, BmobException e) {
                if (e==null){
                    mView.showError(new ErrorThrowable(12, "登录成功"));
                    mView.loginSuccess();
                }else {
                    mView.showError(new ErrorThrowable(e.getErrorCode(), e.getMessage()));
                }
            }


        });
    }

    public void regist(String userAcount, String password) {
        UserinfoBean bmobUser = new UserinfoBean();
        bmobUser.setUsername(userAcount);
        bmobUser.setPassword(password);
        bmobUser.signUp(new SaveListener<UserinfoBean>() {
            @Override
            public void done(UserinfoBean userinfoBean, BmobException e) {
                if (e==null){
                    mView.showError(new ErrorThrowable(12, "注册成功"));
                }else {
                    mView.showError(new ErrorThrowable(e.getErrorCode(), e.getMessage()));
                }
            }


        });

        //   RequestBody data = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

//        addSubscribe(DataManager.login(data).subscribe(getSubscriber(o -> {
//            mView.loginSuccess();
//        })));
    }
}
