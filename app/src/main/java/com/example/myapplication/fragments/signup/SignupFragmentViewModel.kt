package com.example.myapplication.fragments.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.utilits.REPOSITORY
import com.example.myapplication.database.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupFragmentViewModel(application: Application) : AndroidViewModel(application) {

    fun insert(user: UserModel) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(user)
        }

    suspend fun getUserByName(user: String) = withContext(Dispatchers.IO) {
        REPOSITORY.getUserByName(user)
    }

}