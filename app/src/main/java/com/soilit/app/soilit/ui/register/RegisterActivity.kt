package com.soilit.app.soilit.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.soilit.app.soilit.api.SignupResponse
import com.soilit.app.soilit.databinding.ActivityRegisterBinding
import com.soilit.app.soilit.ui.login.LoginActivity
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val TextInputLayout.text: String
        get() = this.editText?.text?.toString() ?: ""


    companion object {
        private const val TAG = "SignUpActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //button signup
        binding.btnSignup.setOnClickListener {
            val name = binding.username.text
            val email = binding.email.text
            val password = binding.password.text
            if (email != null) {
                if (password != null) {
                    when {
                        name.isEmpty() -> {
                            binding.username.error = "Name is required"
                        }
                        email.isEmpty() -> {
                            binding.email.error = "Email is required"
                        }
                        password.isEmpty() -> {
                            binding.password.error = "Password is required"
                        }
                        else -> {
                            register(name.toString(), email.toString(), password.toString())
                        }
                    }
                }
            }
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        
        binding.toLoginPage.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun register(name: String, email: String, password: String) {
        val client = com.soilit.app.soilit.api.ApiConfig()
            .getApiService().register(name, email, password)
        client.enqueue(object : Callback<SignupResponse> {
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e(TAG, "onResponse: Gagal " + response.message())
                    }
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}

