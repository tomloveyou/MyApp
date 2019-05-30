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
        bmobUser.setHead_bg_url("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=360422528,3968251122&fm=27&gp=0.jpg");
        bmobUser.setAvator_url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559209993055&di=40a122c11f88825fb201c2183cb48e67&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01786557e4a6fa0000018c1bf080ca.png");
        bmobUser.login(mActivity.get(), new SaveListener() {
            @Override
            public void onSuccess() {
                mView.showError(new ErrorThrowable(12, "登录成功"));
                mView.loginSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                mView.showError(new ErrorThrowable(i, s));
            }
        });
    }

    public void regist(String userAcount, String password) {
        UserinfoBean bmobUser = new UserinfoBean();
        bmobUser.setUsername(userAcount);
        bmobUser.setPassword(password);
        bmobUser.signUp(mActivity.get(), new SaveListener() {
            @Override
            public void onSuccess() {
                mView.showError(new ErrorThrowable(12, "注册成功"));

            }

            @Override
            public void onFailure(int i, String s) {
                mView.showError(new ErrorThrowable(i, s));
            }
        });

        //   RequestBody data = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

//        addSubscribe(DataManager.login(data).subscribe(getSubscriber(o -> {
//            mView.loginSuccess();
//        })));
    }
}
