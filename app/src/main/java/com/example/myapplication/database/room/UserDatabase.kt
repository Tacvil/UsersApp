package com.example.myapplication.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.database.models.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        private var database: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "database_users"
                ).build()
                database as UserDatabase
            } else database as UserDatabase
        }
    }
}