package com.example.login_register_mvvm_retrofit_android.network

import com.example.login_register_mvvm_retrofit_android.model.LoginReq
import com.example.login_register_mvvm_retrofit_android.model.LoginRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("/login")
    fun login(@Body loginReq: LoginReq): Call<LoginRes>
}