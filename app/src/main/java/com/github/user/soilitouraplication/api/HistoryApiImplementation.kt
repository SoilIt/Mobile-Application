package com.github.user.soilitouraplication.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class HistoryApiImplementation(private val retrofit: Retrofit) : HistoryApi {
    private val historyService: HistoryService by lazy {
        retrofit.create(HistoryService::class.java)
    }

    override fun getHistory(userId: String): Call<HistoryResponse> {
        return historyService.getHistory(userId)
    }
    
    override fun deleteHistory(historyId: String): Call<HistoryResponse> {
        return historyService.deleteHistory(historyId)
    }
    
    private interface HistoryService {
        @GET("/history/{userId}")
        fun getHistory(@Path("userId") userId: String): Call<HistoryResponse>
        
        @GET("/history/delete/{historyId}")
        fun deleteHistory(@Path("historyId") historyId: String): Call<HistoryResponse>
    }
}