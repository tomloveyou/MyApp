package com.yl.userlibrary;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.snackbar.Snackbar;
import com.standards.library.arounter.ARouterConstant;
import com.standards.library.arounter.ARouterUtils;
import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.util.ToastUtil;
import com.yl.userlibrary.mvp.module.UsedLogBean;

import cn.bmob.v3.BmobACL;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import io.reactivex.functions.Consumer;

@Route(path = ARouterConstant.ACTIVITY_USER_UPDATE_INFO_ACTIVITY)
public class UserInfoEditActivity extends BaseUserInfoActivity {
    private EditText activityUserChangeInfoEdit;
    @Autowired
    public int type;
    private String tv_hint_tip;
    private String origin_text;
    private UsedLogBean usedLogBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo_edit_layout;
    }

    @Override
    protected void init() {
        super.init();
        ARouterUtils.injectActivity(this);
        if (type == 0) {
            tv_hint_tip = "昵称";
            origin_text=userinfoBean.getNickname();
        } else {
            tv_hint_tip = "签名";
            origin_text=userinfoBean.getPersonal_sign();
        }

        setTitle("用户信息管理");
        initView();

    }

    @Override
    protected void setListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String content=activityUserChangeInfoEdit.getText().toString();
        if (item.getItemId() == R.id.action_submit) {
            if (TextUtils.isEmpty(content)) {
                ToastUtil.showToast(tv_hint_tip + "不能为空");
                return true;
            }
            if (type == 0) {
                userinfoBean.setNickname(content);
            } else {
                userinfoBean.setPersonal_sign(content);

            }
            userinfoBean.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (type == 0) {
                        usedLogBean.setUsed_type(TextUtils.isEmpty(origin_text) ? "0" : "1");
                    } else {
                        usedLogBean.setUsed_type(TextUtils.isEmpty(origin_text) ? "0" : "1");

                    }
                    usedLogBean.setUsed_content(usedLogBean.getdesc());
                    BmobACL bmobACL = new BmobACL();
                    //设置此帖子为当前用户可写
                    bmobACL.setWriteAccess(userinfoBean, true);
                    //设置此帖子为某种角色可读
                    bmobACL.setReadAccess(userinfoBean, true);
                    usedLogBean.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            finish();

                        }
                    });

                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        usedLogBean=new UsedLogBean();
        usedLogBean.setUsed_username(userinfoBean.getNickname());
        usedLogBean.setUsed_userAcount(userinfoBean.getUsername());
        activityUserChangeInfoEdit = (EditText) findViewById(R.id.activity_user_change_info_edit);
        activityUserChangeInfoEdit.setHint("请输入" + tv_hint_tip);
        activityUserChangeInfoEdit.setText(origin_text);
        usedLogBean.setUsed_module(tv_hint_tip);
    }
}
