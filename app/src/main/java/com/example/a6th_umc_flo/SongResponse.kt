package com.example.a6th_umc_flo

import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: FloChartResult,
)

data class FloChartResult(
    @SerializedName("songs") val songs: ArrayList<FloChartSongs>
)

data class FloChartSongs(
    @SerializedName("isSuccess") val songIdx: Int,
    @SerializedName("isSuccess") val albumIdx: Int,
    @SerializedName("isSuccess") val singer: String,
    @SerializedName("isSuccess") val title: String,
    @SerializedName("isSuccess") val coverImgUrl: String,
)