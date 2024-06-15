package com.example.a6th_umc_flo

import retrofit2.Call
import retrofit2.http.GET

interface SongRetrofitInterfaces {
    @GET("/songs")
    fun getSongs() : Call<SongResponse>
}