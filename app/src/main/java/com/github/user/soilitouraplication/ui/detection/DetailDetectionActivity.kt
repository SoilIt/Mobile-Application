@file:Suppress("SameParameterValue", "ControlFlowWithEmptyBody")

package com.github.user.soilitouraplication.ui.detection

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.github.user.soilitouraplication.R
import com.github.user.soilitouraplication.api.HistoryPostResponse
import com.github.user.soilitouraplication.api.PostDetectionApi
import com.github.user.soilitouraplication.api.TemperatureResponse
import com.github.user.soilitouraplication.database.HistoryDao
import com.github.user.soilitouraplication.databinding.ActivityDetailDetectionBinding
import com.github.user.soilitouraplication.ui.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailDetectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDetectionBinding
    private var byteArray: ByteArray? = null

    @Inject
    lateinit var historyDao: HistoryDao

    data class SoilDetail(val label: String, val description: String)
    data class PlantRecommendation(val label: String, val plants: List<String>)
    data class TvTextResult(val label: String, val data: String)

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailDetectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnsave.setOnClickListener {
            postToAPI()
        }

        binding.btnexporttopdf.setOnClickListener {
            exportToPdf()
        }

        binding.btndetectsoildetail.setOnClickListener {
            showToast("Getting data from Soil Sensor...") // Show initial toast message

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url("http://192.168.0.9/temperature")
                        .build()

                    val response = client.newCall(request).execute()
                    val responseBody = response.body?.string()

                    val gson = Gson()
                    val temperatureResponse =
                        gson.fromJson(responseBody, TemperatureResponse::class.java)
                    val temperatureValue = temperatureResponse.value

                    withContext(Dispatchers.Main) {
                        binding.temperature.text = temperatureValue.toString()
                        showToast("Soil Temperature: $temperatureValue") // Update toast message
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        showToast("Soil Sensor Not Connected") // Display error toast message
                    }
                }
            }
        }

        val results = intent.getStringExtra("results")
        val tvTextResultsText = intent.getStringExtra("tvTextResults")
        val currentDate = intent.getStringExtra("currentDate")
        val currentTime = intent.getStringExtra("currentTime")
        Log.d("DetailDetectionActivity", "Results: $results")
        Log.d("DetailDetectionActivity", "TvTextResultsText: $tvTextResultsText")
        Log.d("DetailDetectionActivity", "CurrentDate: $currentDate")
        Log.d("DetailDetectionActivity", "CurrentTime: $currentTime")

        if (results != null) {
            // Handle the results
            // ...
        }

        if (tvTextResultsText != null) {
            val tvTextResult = parseTvTextResult(tvTextResultsText)
            binding.tvresultsoil.text = tvTextResult.label

            // Retrieve the soil detail and plant recommendation based on the label
            val soilDetail: SoilDetail?
            val plantRecommendation: PlantRecommendation?
            when {
                tvTextResultsText.lowercase(Locale.getDefault()).contains("gambut") -> {
                    soilDetail = SoilDetail(
                        "gambut",
                        "Tanah gambut merupakan tanah yang terbentuk dari penumpukan sisa dari tumbuhan yang setengah membusuk atau mengalami dekomposisi yang tidak sempurna. Tanah gambut memiliki kandungan bahan organik yang tinggi karena bahan bakunya tersebut adalah sisa- sisa dari tumbuhan, seperti lumut dan pepohonan serta sisa- sisa dari binatang yang telah mati."
                    )
                    plantRecommendation =
                        PlantRecommendation("gambut", listOf("Tanaman Cabai", "Jagung"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("humus") -> {
                    soilDetail = SoilDetail(
                        "humus",
                        "Tanah humus berasal dari ranting, daun, dan bagian tumbuhan yang membusuk dan akhirnya lapuk membentuk tanah subur pada lapisan atmosfer. Tanah humus merupakan tanah yang sangat subur sehingga banyak digunakan untuk menanam. Zat-zat yang terdapat di dalam tanah humus dan memiliki banyak manfaat untuk tanaman, antara lain: fenol, alifatik hidroksia, serta asam karboksilat."
                    )
                    plantRecommendation = PlantRecommendation("humus", listOf("Padi", "Selada"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("aluvial") -> {
                    soilDetail = SoilDetail(
                        "aluvial",
                        "Berdasarkan klasifikasi persebaran tanah aluvial di Indonesia, tanah aluvial atau endapan merupakan salah satu dari jenis-jenis tanah yang dibentuk dari lumpur sungai yang mengendap di daerah dataran rendah yang memiliki tingkat kesuburan yang baik dan cocok untuk lahan pertanian."
                    )
                    plantRecommendation =
                        PlantRecommendation("aluvial", listOf("Bunga Mawar", "Sawi"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("laterit") -> {
                    soilDetail = SoilDetail(
                        "laterit",
                        "Tanah laterit atau tanah merah merupakan tanah yang mempunyai warna merah hingga warna kecoklatan yang terbentuk pada lingkungan yang lembab, dingin, dan mungkin juga genangan- genangan air. Tanah ini mempunyai profil tanah yang dalam, mudah menyerap air, memiliki kandungan bahan organik yang sedang dan memiliki tingkat keasaman (pH) netral."
                    )
                    plantRecommendation =
                        PlantRecommendation("laterit", listOf("Tanaman kopi", "Cengkeh"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("litosol") -> {
                    soilDetail = SoilDetail(
                        "litosol",
                        "Tanah litosol merupakan jenis tanah yang terbentuk dari batuan beku yang berasal dari proses meletusnya gunung berapi dan juga sedimen keras dengan proses pelapukan kimia (dengan menggunakan bantuan organisme hidup) dan fisika (dengan bantuan sinar matahari dan hujan) yang belum sempurna."
                    )
                    plantRecommendation =
                        PlantRecommendation("litosol", listOf("Singkong", "Tanaman Karet"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("kapur") -> {
                    soilDetail = SoilDetail(
                        "kapur",
                        "Tanah kapur adalah jenis tanah yang mengandung tingkat kalsium karbonat yang tinggi atau sering disebut sebagai tanah dengan tingkat pH yang tinggi. Tanah kapur cenderung memiliki drainase yang baik dan mampu menahan kelembaban, tetapi kurang subur karena rendahnya kandungan bahan organik."
                    )
                    plantRecommendation = PlantRecommendation("kapur", listOf("Apel", "Kedelai"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("liat") -> {
                    soilDetail = SoilDetail(
                        "liat",
                        "Tanah liat adalah jenis tanah dengan tekstur yang halus dan butirannya sangat kecil. Tanah liat memiliki struktur yang padat dan rapat, membuatnya sulit untuk mengalirkan air dan udara dengan baik. Dalam pengelolaan tanah liat diperlukan pengolahan tanah yang baik untuk memperbaiki struktur dan drainase, pemupukan, serta penggunaan teknik drainase yang baik dan peningkatan aerasi tanah."
                    )
                    plantRecommendation =
                        PlantRecommendation("liat", listOf("Kayu Putih", "Kentang"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("organosol") -> {
                    soilDetail = SoilDetail(
                        "organosol",
                        "Tanah organosol adalah jenis tanah yang memiliki kandungan bahan organik yang tinggi. Tanah ini umumnya memiliki warna gelap hingga hitam pekat dan memiliki tekstur yang lunak dan lembut. Keberadaan bahan organik yang tinggi memberikan sifat kesuburan yang baik pada tanah ini namun tanah ini rentan terhadap degradasi dan penurunan pH."
                    )
                    plantRecommendation = PlantRecommendation("organosol", listOf("Bayam", "Padi"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("pasir") -> {
                    soilDetail = SoilDetail(
                        "pasir",
                        "Tanah pasir adalah jenis tanah dengan tekstur yang kasar dan berbutir halus. Tanah ini memiliki drainase yang sangat baik, namun memiliki kapasitas menahan air dan unsur hara yang rendah. Tanah ini membutuhkan peningkatan kandungan bahan organik dengan pengomposan atau penggunaan pupuk organik, pengaturan irigasi untuk menjaga kelembaban, serta penggunaan mulsa dan tanaman penutup tanah."
                    )
                    plantRecommendation = PlantRecommendation("pasir", listOf("Singkong", "Kaktus"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("regosol") -> {
                    soilDetail = SoilDetail(
                        "regosol",
                        "Tanah regosol adalah jenis tanah yang terbentuk dari pengendapan material bebatuan yang tidak terkelompok atau terurai dengan baik. Tanah ini cenderung memiliki tekstur kasar, berbutir besar, dan kandungan organik yang rendah. Tanah ini butuh pemupukan yang tepat, pengomposan atau penghijauan, pengendalian erosi, dan pengaturan irigasi."
                    )
                    plantRecommendation = PlantRecommendation("regosol", listOf("Mangga", "Tomat"))
                }

                tvTextResultsText.lowercase(Locale.getDefault()).contains("vulkanik") -> {
                    soilDetail = SoilDetail(
                        "vulkanik",
                        "Tanah vulkanik adalah jenis tanah yang terbentuk dari material vulkanik yang berasal dari aktivitas gunung berapi. Tanah vulkanik umumnya memiliki warna yang sangat gelap hingga hitam. Beberapa tanah vulkanik memiliki tekstur kasar dan berbutir besar, sementara yang lain memiliki tekstur halus dan berbutir halus. Struktur pori-pori tanah vulkanik memungkinkannya memiliki tingkat drainase yang baik."
                    )
                    plantRecommendation = PlantRecommendation("vulkanik", listOf("Bawang", "Sawi"))
                }

                else -> {
                    soilDetail = null
                    plantRecommendation = null
                }
            }

            // Update the UI with the soil detail and plant recommendation
            soilDetail?.let {
                binding.soilDetail.text = it.description
                binding.soilType.text = it.label.replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString()
                }
            }

            plantRecommendation?.let {
                val plantRecommendationText = it.plants.joinToString(", ")
                binding.plantRecommendation.text = plantRecommendationText
            }
        }

        // Receive the image as a ByteArray
        byteArray = intent.getByteArrayExtra("image")

        if (byteArray != null) {
            // Load the image into the ivresultsoil ImageView using Glide
            Glide.with(this)
                .load(byteArray)
                .into(binding.ivresultsoil)
        }

        binding.date.text = " $currentDate"
    }

    private fun parseTvTextResult(tvTextResultsText: String): TvTextResult {
        val labelPrefix = "Label: "
        val dataPrefix = "Data: "

        val labelStartIndex = tvTextResultsText.indexOf(labelPrefix)
        val labelEndIndex = tvTextResultsText.indexOf(",", labelStartIndex)
        val label = if (labelStartIndex != -1 && labelEndIndex != -1) {
            tvTextResultsText.substring(labelStartIndex + labelPrefix.length, labelEndIndex).trim()
        } else {
            ""
        }

        val dataStartIndex = tvTextResultsText.indexOf(dataPrefix)
        val data = if (dataStartIndex != -1) {
            tvTextResultsText.substring(dataStartIndex + dataPrefix.length).trim()
        } else {
            ""
        }

        return TvTextResult(label, data)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Start the main activity when the back button is pressed
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun postToAPI() {
        val user = Firebase.auth.currentUser
        val userId = user?.uid


        val byteArray = intent.getByteArrayExtra("image")
        val soilType = intent.getStringExtra("tvTextResults")
        val temperature = binding.temperature.text.toString()
        val moisture = binding.moisture.text.toString()
        val soilcondition = binding.soilCondition.text.toString()

        if (byteArray != null) {
            val file = File.createTempFile("temp_image", ".jpg")
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(byteArray)
            fileOutputStream.close()

            val fileReqBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val filePart = MultipartBody.Part.createFormData("file", "image.jpg", fileReqBody)

            val userIdBody = (userId ?: "").toRequestBody("text/plain".toMediaTypeOrNull())
            val soilTypeBody = soilType!!.toRequestBody("text/plain".toMediaTypeOrNull())
            val soilMoistureBody =
               moisture.toRequestBody("text/plain".toMediaTypeOrNull())
            val soilTemperatureBody =
                temperature.toRequestBody("text/plain".toMediaTypeOrNull())
            val soilConditionBody =
                soilcondition.toRequestBody("text/plain".toMediaTypeOrNull())

            val retrofit = Retrofit.Builder()
                .baseUrl("https://soilit-api-iwwdg24ftq-et.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(PostDetectionApi::class.java)

            val call = service.postHistory(
                filePart,
                userIdBody,
                soilTypeBody,
                soilMoistureBody,
                soilTemperatureBody,
                soilConditionBody
            )

            call.enqueue(object : Callback<HistoryPostResponse> {
                override fun onResponse(
                    call: Call<HistoryPostResponse>,
                    response: Response<HistoryPostResponse>,
                ) {
                    if (response.isSuccessful) {
                        val historyPostResponse = response.body()
                        val message = historyPostResponse?.message
                        Log.i("Retrofit", "Data uploaded successfully! Message: $message")
                        showToast("Data uploaded successfully !")
                        navigateToMainActivity()
                    } else {
                        val message = response.message()
                        Log.e("Retrofit", "Data upload failed! Message: $message")
                        showToast("Data upload failed !")
                    }
                }

                override fun onFailure(call: Call<HistoryPostResponse>, t: Throwable) {
                    val message = t.localizedMessage
                    Log.e("Retrofit", "Error: $message")
                    showToast("Error: $message")

                }
            })
        }
    }


    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@DetailDetectionActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun exportToPdf() {
        val pdfDocument = PdfDocument()
        val pageInfo =
            PdfDocument.PageInfo.Builder(binding.root.width, binding.root.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        Paint()

        val background = ContextCompat.getDrawable(this, R.drawable.background)
        background?.setBounds(0, 0, binding.root.width, binding.root.height)
        background?.draw(canvas)

        canvas.save()
        canvas.translate(0f, 0f)
        binding.root.draw(canvas)
        canvas.restore()

        pdfDocument.finishPage(page)

        val dir = File(Environment.getExternalStorageDirectory().toString() + "/PDF")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val fileName = "soil_report_${System.currentTimeMillis()}.pdf"
        val file = File(dir, fileName)
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(
                this,
                "PDF saved successfully: ${file.absolutePath}",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to save PDF", Toast.LENGTH_SHORT).show()
        }

        pdfDocument.close()
    }
}
