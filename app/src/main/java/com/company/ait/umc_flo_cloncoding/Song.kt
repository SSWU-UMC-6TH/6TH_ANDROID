package com.company.ait.umc_flo_cloncoding

data class Song(
    val title : String = "",
    val singer : String = "",
    var second: Int = 0,
    var playTime: Int = 0,
    var isPlaying: Boolean = false
)
