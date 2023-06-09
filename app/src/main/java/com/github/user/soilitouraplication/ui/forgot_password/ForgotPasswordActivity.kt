package com.github.user.soilitouraplication.ui.forgot_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.user.soilitouraplication.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}