package com.example.minipos.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minipos.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository()

    fun login(
        employeeCode: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {

        viewModelScope.launch {

            val response = repository.login(
                employeeCode,
                password
            )

            onResult(
                response.success,
                response.message
            )
        }
    }
}