package com.example.login_register_mvvm_retrofit_android.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.login_register_mvvm_retrofit_android.Injection
import com.example.login_register_mvvm_retrofit_android.Repo.MainRepository

//파라미터가 있는 Repository를 위해 필요한 것?
class ViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory() {
    // Test suite에 포함되선 안되는 Test Class나 Test mthod에 사용하는 어노테이션
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T{
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object{
        // Volatile : 휘발성 / 해당 변수가 메인메모리에만 적재된다. 즉, 캐시에 저장되지 않는다.
        @Volatile
        private var instance : ViewModelFactory? = null
        fun getInstance(context : Context) : ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}