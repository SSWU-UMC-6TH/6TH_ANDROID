package com.example.a6th_umc_w10_template.ui.login

import com.example.a6th_umc_w10_template.data.remote.auth.Auth

interface LoginView {
    fun onLoginLoading()
    fun onLoginSuccess(auth: Auth)
    fun onLoginFailure(code: Int, message: String)
}