package com.yl.markremember.db.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yl.markremember.db.model.UserInfo


@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id=:id")
    fun getUserById(id: Long): LiveData<UserInfo>

    @Query("SELECT * FROM user")
    fun loadAllUser(): LiveData<List<UserInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userInfo: UserInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(userInfo: List<UserInfo>)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}