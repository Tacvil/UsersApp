package com.example.myapplication.database

import com.example.myapplication.database.models.UserModel

interface DatabaseRepository {
    fun allUsers(): List<UserModel>

    fun insert(user: UserModel)

    fun getUserByName(user: String): Boolean

    fun getUserByNameAndPassword(name: String, password: String): Boolean
}