package com.github.user.soilitouraplication.ui.fullcampaign
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.user.soilitouraplication.api.CampaignApi
import com.github.user.soilitouraplication.api.RetrofitClient
import com.github.user.soilitouraplication.api.UserResponse
import com.github.user.soilitouraplication.databinding.ActivityFullCampaignBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FullCampaign : AppCompatActivity() {
    private lateinit var binding: ActivityFullCampaignBinding
    private lateinit var adapter: FullCampaignAdapter

    private val campaignApi: CampaignApi = RetrofitClient.apiInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullCampaignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FullCampaignAdapter()
        binding.rvfullcampaign.adapter = adapter
        binding.rvfullcampaign.layoutManager = LinearLayoutManager(this)

        // Fetch campaigns from the API
        fetchCampaigns()
    }

    private fun fetchCampaigns() {
        campaignApi.getCampaigns().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val campaignList = userResponse?.data ?: emptyList()
                    adapter.setList(campaignList)
                } else {
                    // Handle request failure
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Handle network error
            }
        })
    }
}
