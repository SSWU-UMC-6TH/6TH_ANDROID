package com.example.a6th_umc_w10_template.ui.splash

import android.os.Handler
import android.os.Looper
import com.example.a6th_umc_w10_template.data.remote.auth.AuthService
import com.example.a6th_umc_w10_template.databinding.ActivitySplashBinding
import com.example.a6th_umc_w10_template.ui.BaseActivity
import com.example.a6th_umc_w10_template.ui.login.LoginActivity
import com.example.a6th_umc_w10_template.ui.main.MainActivity

class SplashActivity: BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate), SplashView {

    override fun initAfterBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
            autoLogin()
        }, 2000)
    }

    private fun autoLogin() {
        AuthService.autoLogin(this)
    }

    override fun onAutoLoginLoading() {

    }

    override fun onAutoLoginSuccess() {
        startActivityWithClear(MainActivity::class.java)
    }

    override fun onAutoLoginFailure(code: Int, message: String) {
        startActivityWithClear(LoginActivity::class.java)
    }
}