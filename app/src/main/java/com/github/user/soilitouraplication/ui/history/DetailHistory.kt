package com.github.user.soilitouraplication.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.github.user.soilitouraplication.R
import com.github.user.soilitouraplication.api.History
import com.github.user.soilitouraplication.databinding.ActivityDetailHistoryBinding
import com.github.user.soilitouraplication.utils.DateUtils

@Suppress("DEPRECATION")
class DetailHistory : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val history = intent.getParcelableExtra<History>("history")
        history?.let {
            Glide.with(this)
                .load(it.image)
                .into(binding.ivresultsoil)
            binding.soilType.text = it.soil_type
            binding.date.text = DateUtils.formatDateTime(it.created_at)
            binding.temperature.text = it.soil_temperature
            binding.moisture.text = it.soil_moisture
            binding.soilCondition.text = it.soil_condition
//            binding.soilDetail.text = it.soil_detail
//            binding.plantRecommendation.text = it.plant_recommendation
        }
    }
}