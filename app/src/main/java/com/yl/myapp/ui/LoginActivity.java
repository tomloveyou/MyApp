package com.yl.myapp.ui;

import android.content.Intent;
import androidx.annotation.NonNull;

import android.widget.Button;
import android.widget.EditText;


import com.ns.yc.ycutilslib.switchButton.SwitchButton;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.myapp.MainActivity;
import com.yl.myapp.R;

import com.yl.myapp.ui.mvp.contract.UserContract;
import com.yl.myapp.ui.mvp.presenter.UserPresenterImpl;

import rx.functions.Action1;

/**
 * A login screen that offers login via email/password.
 */
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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

