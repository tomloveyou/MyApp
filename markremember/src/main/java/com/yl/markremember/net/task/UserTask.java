package com.yl.markremember.net.task;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;


import com.yl.markremember.db.DbManager;
import com.yl.markremember.net.HttpClientManager;
import com.yl.markremember.net.UserService;

import static android.content.Context.MODE_PRIVATE;

public class UserTask {
    private UserService userService;
    private Context context;
    private DbManager dbManager;



    public UserTask(Context context) {
        this.context = context.getApplicationContext();
        userService = HttpClientManager.getInstance(context).getClient().createService(UserService.class);
        dbManager = DbManager.getInstance(context.getApplicationContext());


    }




}
