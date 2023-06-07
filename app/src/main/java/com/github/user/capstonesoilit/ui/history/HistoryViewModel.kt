package com.github.user.capstonesoilit.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.user.capstonesoilit.api.History
import com.github.user.capstonesoilit.api.HistoryApi
import com.github.user.capstonesoilit.api.HistoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class HistoryViewModel(private val historyApi: HistoryApi) : ViewModel() {
    val historyList: MutableLiveData<List<History>> = MutableLiveData()

    fun fetchHistory() {
        val call = historyApi.getHistory()
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
            }
        })
    }
}
