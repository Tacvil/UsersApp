package com.example.myapplication.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserModel(
    @ColumnInfo(name = "nameUser") var nameUser: String?,
    @ColumnInfo(name = "passwordUser") var passwordUser: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}