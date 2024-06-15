package com.example.a6th_umc_flo.ui.signin

import com.example.a6th_umc_flo.data.remote.Result

interface LoginView {
    fun onSignUpSuccess(code : Int, result: Result)
    fun onSignUpFailure()
}