package com.example.login_register_mvvm_retrofit_android.Repo

import com.example.login_register_mvvm_retrofit_android.model.LoginReq
import com.example.login_register_mvvm_retrofit_android.network.datastore.RemoteDataSource

class MainRepository(private val remoteDataSource: RemoteDataSource) {
    fun login(LoginReq: LoginReq) = remoteDataSource.register(LoginReq)
}