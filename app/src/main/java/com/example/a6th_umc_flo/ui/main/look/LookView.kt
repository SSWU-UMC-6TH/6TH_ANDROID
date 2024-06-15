package com.example.a6th_umc_flo.ui.main.look

import com.example.a6th_umc_flo.ui.song.FloChartResult

interface LookView {
    fun onGetSongLoading()
    fun onGetSongSuccess(code: Int, result: FloChartResult)
    fun onGetSongFailure(code: Int, message: String)
}