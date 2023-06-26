package com.example.login_register_mvvm_retrofit_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_register_mvvm_retrofit_android.Repo.MainRepository
import com.example.login_register_mvvm_retrofit_android.model.LoginReq
import com.example.login_register_mvvm_retrofit_android.model.LoginRes
import kotlinx.coroutines.launch
import retrofit2.Callback

class LoginViewModel(private val repository : MainRepository) : ViewModel(){

    fun login(loginReq: LoginReq) = viewModelScope.launch {
        Log.d("lgoinViewModel", "login: ${loginReq.email} ${loginReq.password}")
        repository.login(loginReq).collect{
            it.enqueue(object : Callback<LoginRes>{
                override fun onResponse(call: retrofit2.Call<LoginRes>, response: retrofit2.Response<LoginRes>) {
                    Log.d("login", "onResponse: ${response.body()}")
                    val dataToken = response.body()?.data?.token
                }

                override fun onFailure(call: retrofit2.Call<LoginRes>, t: Throwable) {
                    Log.d("login", "onFailure: ${t.message}")
                }
            })
        }
    }
}