package com.example.minipos.data.repository

import com.example.minipos.data.api.RetrofitClient
import com.example.minipos.data.model.LoginRequest
import com.example.minipos.data.model.LoginResponse

class LoginRepository {

    suspend fun login(
        employeeCode: String,
        password: String
    ): LoginResponse {

        /*
         * Gọi API
         *
         * return RetrofitClient.apiService.login(
         *     LoginRequest(
         *         employeeCode = employeeCode,
         *         password = password
         *     )
         * )
         */

        return mockLogin(employeeCode, password)
    }

    private fun mockLogin(
        employeeCode: String,
        password: String
    ): LoginResponse {

        return if (
            employeeCode == "NV001" &&
            password == "123456"
        ) {

            LoginResponse(
                success = true,
                message = "Đăng nhập thành công",
                token = "mock-token-123"
            )

        } else {

            LoginResponse(
                success = false,
                message = "Sai mã nhân viên hoặc mật khẩu",
                token = null
            )
        }
    }
}