package com.yl.myapp.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.yl.myapp.MainActivity;
import com.yl.myapp.R;
import com.yl.myapp.base.BaseFuncActivity;
import com.yl.myapp.ui.mvp.contract.UserContract;
import com.yl.myapp.ui.mvp.presenter.UserPresenterImpl;

import rx.functions.Action1;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseFuncActivity<UserPresenterImpl> implements UserContract.UserLoginView {


    private EditText email;
    private EditText password;
    private Button emailSignInButton;

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
        initView();
    }

    @Override
    protected void setListener() {
        ClickView(emailSignInButton).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                mPresenter.login(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void initView() {

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

