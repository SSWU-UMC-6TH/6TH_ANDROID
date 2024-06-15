package com.example.a6th_umc_flo

interface LookView {
    fun onGetSongLoading()
    fun onGetSongSuccess(code: Int, result: FloChartResult)
    fun onGetSongFailure(code: Int, message: String)
}