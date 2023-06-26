package com.example.login_register_mvvm_retrofit_android

import android.content.Context
import com.example.login_register_mvvm_retrofit_android.Repo.MainRepository
import com.example.login_register_mvvm_retrofit_android.network.ApiConfig
import com.example.login_register_mvvm_retrofit_android.network.datastore.RemoteDataSource

object Injection {
    fun provideRepository(context : Context) : MainRepository {
        val apiServices = ApiConfig.getApiService()
        val remoteDataSource = RemoteDataSource(apiServices)
        return MainRepository(remoteDataSource)
    }
}