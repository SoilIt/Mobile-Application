package com.github.user.soilit.api


import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService{
    @POST("v1/register")
    @FormUrlEncoded
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignupResponse>


    @POST("v1/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("v1/stories")
    fun getAllStories(
        @Header("Authorization") Bearer: String
    ): Call<GetAllStoryResponse>

    @Multipart
    @POST("v1/stories")
    fun uploadStories(
        @Header("Authorization") Bearer: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ): Call<AddNewStoryResponse>

}

class ApiConfig{
    fun getApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://story-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

