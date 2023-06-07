package com.github.user.capstonesoilit.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface CampaignApi {
    @GET("/campaign/limit")
    fun getCampaigns(): Call<UserResponse>
}

interface HistoryApi {
    @GET("/history")
    fun getHistory(): Call<HistoryResponse>
}


interface PostDetectionApi {
    @POST("/history")
    fun getCampaigns(): Call<HistoryResponse>
}


