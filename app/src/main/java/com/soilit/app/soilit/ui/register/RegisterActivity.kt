package com.soilit.app.soilit.ui.register

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.soilit.app.soilit.api.SignupResponse
import com.soilit.app.soilit.databinding.ActivityRegisterBinding
import com.soilit.app.soilit.ui.login.LoginActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.soilit.app.soilit.R
import com.soilit.app.soilit.ui.main.MainActivity
import com.soilit.app.soilit.utils.Validate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    
    private lateinit var auth: FirebaseAuth
    
    private lateinit var googleSignInClient: GoogleSignInClient
    
    private lateinit var db: FirebaseFirestore
    
    private val TextInputLayout.text: String
        get() = this.editText?.text?.toString() ?: ""
    
    
    companion object {
        private const val TAG = "SignUpActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initializeAuth()
        
        initializeFirestore()
        
        initializeGoogleSignIn()
        
        setupListeners()
        
        setupFieldValidations()
    }
    
    private fun initializeGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }
    
    private fun initializeAuth() {
        auth = FirebaseAuth.getInstance()
    }
    
    private fun initializeFirestore() {
        db = FirebaseFirestore.getInstance()
    }
    
    private fun setupListeners() {
        binding.btnSignup.setOnClickListener {
            doSignUp()
        }
        
        binding.signInGoogle.setOnClickListener {
            doSignInWithGoogle()
        }
        
        binding.toLoginPage.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
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
        
        binding.confirmPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateConfirmPassword()
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
        
        binding.confirmPassword.addTextChangedListener {
            validateConfirmPassword()
            enableButton()
        }
    }
    
    private fun validateForm(): Boolean {
        return validateEmail() && validatePassword() && validateConfirmPassword()
    }
    
    private fun enableButton() {
        binding.btnSignup.isEnabled = validateForm()
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
    
    private fun validateConfirmPassword(): Boolean {
        val password = binding.password.text
        val confirmPassword = binding.confirmPassword.text
        
        binding.confirmPasswordLayout.error =
            Validate.validateConfirmPassword(password, confirmPassword)
        
        return binding.confirmPasswordLayout.error == null
    }
    
    private fun onLoading(active:Boolean) {
        binding.btnSignup.isEnabled = !active
        binding.signInGoogle.isEnabled = !active
        binding.toLoginPage.isEnabled = !active
    }
    
    private fun doSignUp() {
        onLoading(true)
        
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    
                    if (user != null) {
                        
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                val user = auth.currentUser
                                
                                if (user != null) {
                                    val uid = user.uid
                                    val name = binding.username.text.toString()
                                    
                                    val data = hashMapOf(
                                        "uid" to uid,
                                        "name" to name,
                                        "email" to email
                                    )
                                    
                                    db.collection("users")
                                        .document(uid)
                                        .set(data)
                                        .addOnSuccessListener {
                                            Log.d(TAG, "DocumentSnapshot successfully written!")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(TAG, "Error writing document", e)
                                        }
                                }
                                
                                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                
                                startActivity(intent)
    
                                onLoading(false)
                            }
                    } else {
                        onLoading(false)
                    }
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error : ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    onLoading(false)
                }
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
                Log.d("TAG", "GOOGLE : NOK RESULT ${result.resultCode} ${result.data}")
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
            Log.d("TAG", "GOOGLE : NOK ")
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
        val main = Intent(this@RegisterActivity, MainActivity::class.java)
        startActivity(main)
        finish()
    }
}

