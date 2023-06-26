package com.example.login_register_mvvm_retrofit_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.login_register_mvvm_retrofit_android.R
import com.example.login_register_mvvm_retrofit_android.databinding.ActivityMainBinding
import com.example.login_register_mvvm_retrofit_android.model.LoginReq
import com.example.login_register_mvvm_retrofit_android.viewmodel.LoginViewModel
import com.example.login_register_mvvm_retrofit_android.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    // loginViewModel이 MainActivity의 VM임
    // private lateinit var viewModel : LoginViewModel 이후 viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)로 초기화
    // 하지 않고 by를 통해 지연생성할 수 있다. (jvmTarget 1.8이상부터 가능한 것 같다?)
    private val loginViewModel : LoginViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            when {
                email.isEmpty() -> {
                    binding.edtEmail.error = "이메일을 입력해주세요."
                    binding.edtEmail.requestFocus()
                }
                password.isEmpty() -> {
                    binding.edtPassword.error = "비밀번호를 입력해주세요."
                    binding.edtPassword.requestFocus()
                }
                else -> {
                    val loginReq = LoginReq(email, password)
                    loginViewModel.login(loginReq)
                }
            }
        }
    }
}