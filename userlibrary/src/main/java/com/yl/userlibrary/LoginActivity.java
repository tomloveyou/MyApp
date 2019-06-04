package com.yl.userlibrary;

import android.content.Intent;
import androidx.annotation.NonNull;

import android.widget.Button;
import android.widget.EditText;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.ns.yc.ycutilslib.switchButton.SwitchButton;
import com.standards.library.arounter.ARouterConstant;
import com.standards.library.arounter.ARouterUtils;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.userlibrary.mvp.contract.UserContract;
import com.yl.userlibrary.mvp.presenter.UserPresenterImpl;


import rx.functions.Action1;

@Route(path = ARouterConstant.ACTIVITY_USER_LOGIN_ACTIVITY)
public class LoginActivity extends BaseTitleBarActivity<UserPresenterImpl> implements UserContract.UserLoginView {


    private EditText email;
    private EditText password;
    private Button emailSignInButton;
    private SwitchButton switchButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public UserPresenterImpl getPresenter() {
        return new UserPresenterImpl(this);
    }

    @Override
    protected void init() {
        setTitle("登录");
        initView();
    }

    @Override
    protected void setListener() {
        emailSignInButton.setText(switchButton.isChecked()?"注册":"登录");
        ClickView(emailSignInButton).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                if (switchButton.isChecked()){

                    mPresenter.regist(email.getText().toString(), password.getText().toString());
                }else {
                    mPresenter.login(email.getText().toString(), password.getText().toString());
                }

            }
        });
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                emailSignInButton.setText(isChecked?"注册":"登录");
                setTitle(isChecked?"注册":"登录");
            }
        });

    }

    private void initView() {
        switchButton = findView(R.id.btn_login_or_regist);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    }

    @Override
    public void loginSuccess() {
        ARouterUtils.navigation(ARouterConstant.ACTIVITY_MAIN_ACTIVITY);
        finish();
    }
}

