package com.github.user.soilit.api

data class User(
    val author: String,
    val description: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class User_sensor(
    val id: String,
    val intensity: String
)

data class Message(
    val message: String,
    val id: String,
    val time: String
)
