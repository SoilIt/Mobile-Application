package com.github.user.soilit.api.sensorapi

import com.github.user.soilit.api.model.User_sensor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface awsGetApi {
    @GET("sensor-data/")
    fun getUsers(): Call<List<User_sensor>>

}

interface JSonPlaceHolder {
    @POST("sensor-data/{id}")
    fun senduserdata(
        @Body user: User_sensor
    ): Call<User_sensor>

}