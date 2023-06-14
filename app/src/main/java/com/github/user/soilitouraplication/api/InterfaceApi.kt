package com.github.user.soilitouraplication.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CampaignApi {
    @GET("/campaign/limit")
    fun getCampaigns(): Call<UserResponse>
}

interface HistoryApi {
    @GET("/history/{id}")
    fun getHistory(@Path("id") userId: String): Call<HistoryResponse>
    
    @DELETE("/history/delete/{id}")
    fun deleteHistory(@Path("id") historyId: String): Call<HistoryResponse>
}

interface FaqApi {
    @GET("/faq")
    fun getFaq(): Call<FaqResponse>
}

interface PostDetectionApi {
    @POST("/history")
    fun postHistory(@Body history: History): Call<HistoryResponse>
}