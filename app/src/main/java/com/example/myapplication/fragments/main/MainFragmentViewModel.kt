package com.example.myapplication.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {

    suspend fun allUsers() =  withContext(Dispatchers.IO) {
        REPOSITORY.allUsers()
    }
}