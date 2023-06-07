package com.github.user.capstonesoilit.ui.home

import RetrofitClient
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.user.capstonesoilit.api.Campaign
import com.github.user.capstonesoilit.api.UserResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.CacheControl
import okhttp3.Request

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val campaigns = MutableLiveData<List<Campaign>>()
    val isLoading = MutableLiveData<Boolean>()
    private var savedState: List<Campaign>? = null

    internal fun fetchCampaigns() {
        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Cek apakah koneksi internet tersedia
                if (isNetworkAvailable()) {
                    val request = Request.Builder()
                        .url("https://private-7a3f77-soilit.apiary-mock.com/campaign/limit")
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build()

                    val response = RetrofitClient.okHttpClient.newCall(request).execute()

                    if (response.isSuccessful) {
                        val responseData = response.body()?.string()
                        val userResponse =
                            Gson().fromJson(responseData, UserResponse::class.java)
                        // If there is a saved state, use it instead of the fetched data
                        val campaignList = savedState ?: userResponse.data
                        campaigns.postValue(campaignList)
                    } else {
                        Log.d("Failure", "Request failed with code: ${response.code()}")
                    }
                    isLoading.postValue(false)
                } else {
                    // Jika tidak ada koneksi internet, tampilkan loader
                    isLoading.postValue(true)
                }
            } catch (e: Exception) {
                Log.d("Failure", e.message ?: "")
            }
        }
    }

    // Fungsi untuk mengecek apakah ada koneksi internet
    private fun isNetworkAvailable(): Boolean {
        val context = getApplication<Application>().applicationContext
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.run {
                    return when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
        }
        return false
    }

    fun getCampaigns(): LiveData<List<Campaign>> {
        return campaigns
    }

    fun saveState(outState: Bundle) {
        // Save the current state to the ViewModel
        campaigns.value?.let {
            savedState = it
            outState.putParcelableArrayList(KEY_SAVED_STATE, ArrayList(it))
        }
    }

    fun restoreState(state: Bundle?) {
        // Restore the saved state to be used later
        savedState = state?.getParcelableArrayList<Campaign>(KEY_SAVED_STATE)?.toList()
    }

    companion object {
        private const val KEY_SAVED_STATE = "saved_state"
    }
}
