package com.soilit.app.soilit.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soilit.app.soilit.databinding.ActivityLoginBinding
import com.soilit.app.soilit.preferences.SharedPreferences
import com.soilit.app.soilit.ui.main.MainActivity
import com.soilit.app.soilit.ui.register.RegisterActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sph: SharedPreferences
    
    private val TextInputLayout.text: String
        get() = this.editText?.text?.toString() ?: ""

    companion object{
        private const val TAG = "LoginActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sph = SharedPreferences(this)
        if (sph.getStatusLogin()){
            val main = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(main)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val main = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(main)
        }
        
        binding.toRegisterPage.setOnClickListener {
            val register = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(register)
        }
    }
}