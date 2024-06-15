package com.example.a6th_umc_flo

interface LoginView {
    fun onSignUpSuccess(code : Int, result: Result)
    fun onSignUpFailure()
}