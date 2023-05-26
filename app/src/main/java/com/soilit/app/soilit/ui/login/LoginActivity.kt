package com.soilit.app.soilit.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.soilit.app.soilit.R
import com.soilit.app.soilit.databinding.ActivityLoginBinding
import com.soilit.app.soilit.ui.main.MainActivity
import com.soilit.app.soilit.ui.register.RegisterActivity
import com.soilit.app.soilit.utils.Validate

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    
    private val TextInputLayout.text: String
        get() = this.editText?.text?.toString() ?: ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeAuth()
        initializeGoogleSignIn()
        
        setupListeners()
        checkCurrentUser()
        
        setupFieldValidations()
    }
    
    private fun initializeAuth() {
        auth = FirebaseAuth.getInstance()
    }
    
    private fun initializeGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }
    
    private fun setupListeners() {
        binding.signInGoogle?.setOnClickListener {
            doSignInWithGoogle()
        }
        
        binding.toRegisterPage.setOnClickListener {
            val register = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(register)
        }
        
        binding.btnLogin.setOnClickListener {
            if (validateForm()) {
                doSignIn()
            }
        }
    }
    
    private fun checkCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startMainActivity()
        }
    }
    
    private fun setupFieldValidations() {
        binding.email.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail()
                enableButton()
            }
        }
        
        binding.password.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validatePassword()
                enableButton()
            }
        }
        
        binding.password.addTextChangedListener {
            validatePassword()
            enableButton()
        }
        
        binding.email.addTextChangedListener {
            validateEmail()
            enableButton()
        }
    }
    
    private fun validateForm(): Boolean {
        return validateEmail() && validatePassword()
    }
    
    private fun enableButton() {
        binding.btnLogin.isEnabled = validateForm()
    }
    
    private fun validateEmail(): Boolean {
        val email = binding.email.text
        
        binding.emailLayout.error = Validate.validateEmail(email)
        
        return binding.emailLayout.error == null
    }
    
    private fun validatePassword(): Boolean {
        val password = binding.password.text
        
        binding.passwordLayout.error = Validate.validatePassword(password)
        
        return binding.passwordLayout.error == null
    }
    
    private fun onLoading(active:Boolean) {
        binding.btnLogin.isEnabled = !active
        binding.signInGoogle.isEnabled = !active
        binding.toRegisterPage.isEnabled = !active
    }
    
    private fun doSignIn() {
        onLoading(true)
        
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startMainActivity()
                } else {
                    binding.emailLayout.error = "Email atau password salah"
                    binding.passwordLayout.error = "Email atau password salah"
                }
                
                onLoading(false)
            }
    }
    
    private fun doSignInWithGoogle() {
        onLoading(true)
        
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
    
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            } else {
                onLoading(false)
            }
        }
    
    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            onLoading(false)
            
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                startMainActivity()
            } else {
                Log.d("TAG", "GOOGLE : NOK ")
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
            
            onLoading(false)
        }
    }
    
    private fun startMainActivity() {
        val main = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(main)
        finish()
    }
}
