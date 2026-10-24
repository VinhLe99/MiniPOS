package com.example.minipos.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.minipos.R
import com.example.minipos.ui.pos.PosActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmployeeCode: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtError: TextView

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        edtEmployeeCode = findViewById(R.id.edtEmployeeCode)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtError = findViewById(R.id.txtError)

        btnLogin.setOnClickListener {

            val employeeCode =
                edtEmployeeCode.text.toString().trim()

            val password =
                edtPassword.text.toString()

            if (employeeCode.isEmpty()) {
                showError("Vui lòng nhập mã nhân viên")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                showError("Vui lòng nhập mật khẩu")
                return@setOnClickListener
            }

            hideError()

            viewModel.login(
                employeeCode,
                password
            ) { success, message ->

                if (success) {

                    val intent = Intent(
                        this,
                        PosActivity::class.java
                    )

                    startActivity(intent)

                    finish()

                } else {

                    showError(
                        message ?: "Đăng nhập thất bại"
                    )
                }
            }
        }

        edtEmployeeCode.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideError()
            }
        }

        edtPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideError()
            }
        }
    }

    private fun showError(message: String) {
        txtError.text = message
        txtError.visibility = View.VISIBLE
    }

    private fun hideError() {
        txtError.visibility = View.GONE
    }
}