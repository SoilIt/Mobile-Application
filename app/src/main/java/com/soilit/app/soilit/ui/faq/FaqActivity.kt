package com.soilit.app.soilit.ui.faq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soilit.app.soilit.databinding.ActivityFaqBinding

class FaqActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaqBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}