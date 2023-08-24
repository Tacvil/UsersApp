package com.example.myapplication.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.models.UserModel
import com.example.myapplication.database.room.UserDatabase
import com.example.myapplication.database.room.UserRepository
import com.example.myapplication.utilits.REPOSITORY
import com.example.myapplication.utilits.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun initDatabase(type: String) {
        when (type) {
            TYPE_ROOM -> {
                val dao = UserDatabase.getInstance(context).getUserDao()
                REPOSITORY = UserRepository(dao)
            }
        }
    }

    fun insert(user: UserModel) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(user)
        }

    suspend fun getUserByNameAndPassword(name: String, password: String) =
        withContext(Dispatchers.IO) {
            REPOSITORY.getUserByNameAndPassword(name, password)
        }

    suspend fun allUsers() = withContext(Dispatchers.IO) {
        REPOSITORY.allUsers()
    }

    suspend fun getUserByName(user: String) = withContext(Dispatchers.IO) {
        REPOSITORY.getUserByName(user)
    }
}