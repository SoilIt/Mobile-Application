package com.github.user.soilitouraplication.ui.detection

import android.graphics.Bitmap

interface SoilClassifier {
    data class Recognition(
        var id: String = "", // A unique identifier for what has been recognized. Specific to the class, not the instance of the object.
        var title: String = "", // Display name for the recognition.
        var confidence: Float = 0F, // A sortable score for how good the recognition is relative to others. Higher should be better.
    ) {
        override fun toString(): String {
            return title
        }
    }

    fun recognizeImage(bitmap: Bitmap): Recognition

    fun close()
}
