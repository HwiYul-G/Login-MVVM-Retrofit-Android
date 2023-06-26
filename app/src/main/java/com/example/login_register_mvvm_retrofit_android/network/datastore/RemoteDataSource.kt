package com.example.login_register_mvvm_retrofit_android.network.datastore

import android.util.Log
import com.example.login_register_mvvm_retrofit_android.model.LoginReq
import com.example.login_register_mvvm_retrofit_android.model.LoginRes
import com.example.login_register_mvvm_retrofit_android.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiServices){
    // https://developer.android.com/kotlin/flow
    /*
        코루틴에서 오직 하나의 값만 return하는 suspend 함수와 달리 flow는 다양한 값들을 연속적으로 방출(emit)한다.
        flow는 비동기 연산을 수행하고 그 결과를 순차적으로 방출하는 비동기 데이터 스트림이다.

        repository는 UI data 생산자이다. UI는 repository로부터 데이터를 받아서 화면에 표시한다.

    */
    fun register(loginReq: LoginReq) = flow {
        emit(apiService.login2(loginReq))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}