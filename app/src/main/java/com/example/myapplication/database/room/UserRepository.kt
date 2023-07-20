package com.example.myapplication.database.room

import com.example.myapplication.database.DatabaseRepository
import com.example.myapplication.database.models.UserModel

class UserRepository(private val userDao: UserDao) : DatabaseRepository {

    override fun allUsers(): List<UserModel> {
        return userDao.getAllUsers()
    }

    override fun insert(user: UserModel) {
        userDao.insert(user)
    }

    override fun getUserByName(user: String): Boolean {
        return userDao.getUserByName(user)
    }

    override fun getUserByNameAndPassword(name: String, password: String): Boolean {
        return userDao.getUserByNameAndPassword(name, password)
    }

}