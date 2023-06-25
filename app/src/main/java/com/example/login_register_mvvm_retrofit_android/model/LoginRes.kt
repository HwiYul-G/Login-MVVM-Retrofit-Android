package com.example.login_register_mvvm_retrofit_android.model

import com.google.gson.annotations.SerializedName

data class LoginRes(
    @field:SerializedName("data")
    val data: LoginResult,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class LoginResult(
    @field:SerializedName("token")
    val token: String
)
