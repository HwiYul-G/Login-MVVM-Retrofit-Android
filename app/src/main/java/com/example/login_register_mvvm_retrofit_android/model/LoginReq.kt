package com.example.login_register_mvvm_retrofit_android.model

import com.google.gson.annotations.SerializedName

data class LoginReq(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
