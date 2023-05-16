package com.github.user.soilit.api

import com.github.user.soilit.api.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    fun getNews(@Query("q") query : String, @Query("apiKey") key : String) : Call<UserResponse>
}