package com.soilit.app.soilit.db

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stories(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String
): Parcelable
