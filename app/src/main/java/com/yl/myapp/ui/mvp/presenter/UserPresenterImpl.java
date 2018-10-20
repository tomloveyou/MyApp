package com.yl.myapp.ui.mvp.presenter;

import android.app.Activity;

import com.yl.myapp.api.DataManager;
import com.yl.myapp.base.BasePresenter;
import com.yl.myapp.base.ILoadingView;
import com.yl.myapp.ui.mvp.contract.UserContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class UserPresenterImpl extends BasePresenter<UserContract.UserLoginView> {
    public UserPresenterImpl(Activity activity) {
        super(activity);
    }

    public void login(String userAcount, String password) {
        Map<String,String> data = new HashMap<>();
        data.put("loginName", userAcount);
        data.put("password", password);
        data.put("platform", "2");

     //   RequestBody data = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        addSubscribe(DataManager.login(data).subscribe(getSubscriber(o -> {
            mView.loginSuccess();
        })));
    }

}
