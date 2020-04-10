package com.yl.markremember.db;

import android.content.Context;

import androidx.room.Room;


import com.huayi.parkmanager.im.db.ParkDatabase;
import com.yl.markremember.db.dao.LabelDao;
import com.yl.markremember.db.dao.UserDao;


/**
 * 数据库管理类
 */
public class DbManager {
    private final String DB_NAME_FORMAT = "user_%s";
    private volatile static DbManager instance;
    private Context context;
    private ParkDatabase database;
    private long currentUserId;

    private DbManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public static DbManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DbManager.class) {
                if (instance == null) {
                    instance = new DbManager(context);
                }
            }
        }

        return instance;
    }

    /**
     * 打开指定用户数据库
     *
     * @param userId
     */
    public void openDb(long userId) {
        if (currentUserId != -1) {
            if (currentUserId!=userId) {
                closeDb();
            } else {
                //SLog.d(LogTag.DB, "user:" + userId + ", has opened db.");
                return;
            }
        } else {
            // do nothing
        }
        currentUserId = userId;
       // String userIdMD5 = MD5.encrypt(userId);
        database = Room.databaseBuilder(context, ParkDatabase.class, String.format(DB_NAME_FORMAT, currentUserId))
                .fallbackToDestructiveMigration()
                .build();
       // SLog.d(LogTag.DB, "openDb,userId:" + currentUserId);
    }

    public void closeDb() {
        if (database != null) {
           // SLog.d(LogTag.DB, "closeDb,userId:" + currentUserId);
            database.close();
        }
        currentUserId = -1;
    }

    public UserDao getUserDao() {
        if (database == null) {
           // SLog.e(LogTag.DB, "Get Dao need openDb first.");
            return null;
        }
        return database.getUserDao();
    }
    public LabelDao getLabelDao() {
        if (database == null) {
            // SLog.e(LogTag.DB, "Get Dao need openDb first.");
            return null;
        }
        return database.getLabelDao();
    }


}
