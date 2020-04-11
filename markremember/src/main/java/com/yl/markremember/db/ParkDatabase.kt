package com.huayi.parkmanager.im.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.luck.picture.lib.tools.DateUtils
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.db.dao.LabelDao
import com.yl.markremember.db.dao.ListDao
import com.yl.markremember.db.dao.UserDao
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo

import com.yl.markremember.db.model.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [UserInfo::class, LabelInfo::class, ListInfo::class], version = 2, exportSchema = false)
@TypeConverters(com.yl.markremember.net.TypeConverters::class)
abstract class ParkDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract val labelDao:LabelDao
    abstract val listDao:ListDao
    private class DatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var listDao=database.listDao

                    // Delete all content here.
                    listDao.deleteAllList()

                    // Add sample user.
                    var listInfo = ListInfo(0,null,"收集箱",DateUtils.timeParse(Date().time),null,0)
                    listDao.insertList(listInfo)
                    var listInfo_welcome = ListInfo(1,null,"welcome",DateUtils.timeParse(Date().time),null,0)
                    listDao.insertList(listInfo)
                    listDao.insertList(listInfo_welcome)
                }
            }
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: ParkDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ParkDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ParkDatabase::class.java,
                        "todo_database"
                ).addCallback(DatabaseCallback(scope)).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                return instance
            }
        }
       val MIGRATION_1_2:Migration =object :Migration(1,2){//数据库升级
           override fun migrate(database: SupportSQLiteDatabase) {
//               database.execSQL("ALTER TABLE department "
//                       + " ADD COLUMN phone_num TEXT");
           }
       }
    }

}
