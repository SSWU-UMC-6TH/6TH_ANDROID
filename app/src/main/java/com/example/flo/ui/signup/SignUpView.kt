package com.example.flo.ui.signup

// Activity와 Auth 서비스를 연결시켜주기 위한 것
interface SignUpView {
    fun onSignUpLoading()
    fun onSignUpSuccess()
    fun onSignUpFailure(code: Int, message: String)
}