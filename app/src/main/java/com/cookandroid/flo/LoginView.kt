package com.cookandroid.flo

interface LoginView {
    fun onLoginSuccess(code: Int, result: Result)
    fun onLoginFailure(message: String)
}