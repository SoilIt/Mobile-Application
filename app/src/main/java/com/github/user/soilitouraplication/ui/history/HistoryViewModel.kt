package com.github.user.soilitouraplication.ui.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.user.soilitouraplication.api.History
import com.github.user.soilitouraplication.api.HistoryApi
import com.github.user.soilitouraplication.api.HistoryResponse
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val historyApi: HistoryApi) : ViewModel() {
    val historyList: MutableLiveData<List<History>> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun fetchHistory() {
        val user = Firebase.auth.currentUser
        val userId = user?.uid

        val call = historyApi.getHistory(userId = userId ?: "")
        call.enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>,
            ) {
                if (response.isSuccessful) {
                    val historyResponse = response.body()
                    historyResponse?.data?.let { historyList ->
                        this@HistoryViewModel.historyList.value = historyList
                    }
                } else {
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                // Handle failure
                Log.e("HistoryViewModel", "Failed to fetch history: ${t.message}")
                // Tambahan: Mengirim pesan kesalahan ke LiveData atau melakukan penanganan lainnya
                errorLiveData.value = "Failed to fetch history. Please try again later."
            }
        })
    }
}
