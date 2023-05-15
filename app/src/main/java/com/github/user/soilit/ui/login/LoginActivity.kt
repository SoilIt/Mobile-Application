package com.github.user.soilit.ui.login

import CustomPassword
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.github.user.soilit.api.ApiConfig
import com.github.user.soilit.api.LoginResponse
import com.github.user.soilit.databinding.ActivityLoginBinding
import com.github.user.soilit.preferences.SharedPreferences
import com.github.user.soilit.ui.main.MainActivity
import com.github.user.soilit.ui.register.RegisterActivity
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sph: SharedPreferences
    private lateinit var passwordInput: CustomPassword
    private lateinit var loginButton: Button
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

        binding.btnSignup.setOnClickListener {
            val register = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(register)
        }

        binding.btnLogin.setOnClickListener {
            val main = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(main)
        }
//            val email = binding.email.text
//            val password = binding.password.text
//            when{
//                email.isEmpty()->{
//                    binding.email.error = "Email is Required"
//                }
//                password.isEmpty()->{
//                    binding.password.error = "Password is Required"
//                }
//                else->{
//                    login(email,password)
//                }
//            }
//        }
    }

//    private fun login(email: String, password: String){
//        val client = ApiConfig().getApiService().login(email,password)
//        client.enqueue(object: Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful){
//                    val responseBody = response.body()
//                    if (responseBody!=null && !responseBody.error){
//                        sph.saveUserToken(responseBody.loginResult.token)
//                        sph.setStatusLogin(true)
//                        val main = Intent(this@LoginActivity, MainActivity::class.java)
//                        startActivity(main)
//                        finishAffinity()
//                    }else{
//                        Log.e(TAG, "onResponse: Gagal" + response.message())
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//
//        })
//    }
}