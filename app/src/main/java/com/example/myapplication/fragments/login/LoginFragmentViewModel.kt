package com.example.myapplication.fragments.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.utilits.REPOSITORY
import com.example.myapplication.utilits.TYPE_ROOM
import com.example.myapplication.database.room.UserDatabase
import com.example.myapplication.database.room.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun initDatabase(type: String) {
        when (type) {
            TYPE_ROOM -> {
                val dao = UserDatabase.getInstance(context).getUserDao()
                REPOSITORY = UserRepository(dao)
            }
        }
    }

    suspend fun getUserByNameAndPassword(name: String, password: String) =
        withContext(Dispatchers.IO) {
            REPOSITORY.getUserByNameAndPassword(name, password)
        }
}