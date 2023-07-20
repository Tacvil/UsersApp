package com.example.myapplication.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.models.UserModel

@Dao
interface UserDao {
    @Query("SELECT * from users_table")
    fun getAllUsers(): List<UserModel>

    @Query("SELECT EXISTS (SELECT * FROM users_table WHERE nameUser = :name)")
    fun getUserByName(name: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM users_table WHERE nameUser = :name AND passwordUser = :password)")
    fun getUserByNameAndPassword(name: String, password: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserModel)
}