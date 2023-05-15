package com.github.user.soilit.utils

import com.github.user.soilit.utils.Constants.OPEN_GOOGLE
import com.github.user.soilit.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("halo") -> {
                when (random) {
                    0 -> "Halo juga!"
                    1 -> "Senang bertemu kamu"
                    else -> "error" }
            }

            //How are you?
            message.contains("bagaimana kabarmu") -> {
                when (random) {
                    0 -> "alhamdulillah"
                    1 -> "aku sehat aja kok"
                    2 -> "baik, kalau kamu ?"
                    else -> "error"
                }
            }

            message.contains("terima kasih") -> {
                when (random) {
                    0 -> "sama-sama"
                    1 -> "terima kasih juga"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("berapa") || message.contains("sekarang")|| message.contains("jam")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "Saya tidak mengerti"
                    1 -> "Coba tanyakan yang lain"
                    else -> "Saya masih belajar memahami anda"
                }
            }
        }
    }
}