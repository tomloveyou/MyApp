package com.huayi.parkmanager.im.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.db.dao.LabelDao
import com.yl.markremember.db.dao.UserDao
import com.yl.markremember.db.model.LabelInfo

import com.yl.markremember.db.model.UserInfo

@Database(entities = [UserInfo::class, LabelInfo::class], version = 1, exportSchema = false)
@TypeConverters(com.yl.markremember.net.TypeConverters::class)
abstract class ParkDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val labelDao:LabelDao
}
