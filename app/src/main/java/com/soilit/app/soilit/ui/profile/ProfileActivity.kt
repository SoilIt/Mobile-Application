package com.soilit.app.soilit.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soilit.app.soilit.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        
        setUser()
        
        toggleEdit()
    }
    
    private fun setUser() {
        val db = Firebase.firestore
        
        val user = Firebase.auth.currentUser
        
        val usedId = user?.uid
        
        db.collection("users").whereEqualTo("uid", usedId)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val username = document.data["name"]
                    val email = document.data["email"]
                    
                    binding.username.setText(username as String)
                    binding.email.setText(email as String)
                }
            }
    }
    
    private fun toggleEdit() {
        binding.btnEditProfile.setOnClickListener {
            binding.isEdit.visibility = android.view.View.VISIBLE
            binding.isView.visibility = android.view.View.GONE
            
            binding.usernameLayout.isClickable = true
            binding.usernameLayout.isEnabled = true
        }
        
        binding.btnEdit.setOnClickListener {
            
            binding.btnEdit.isEnabled = false
            
            val db = Firebase.firestore
            
            val user = Firebase.auth.currentUser
            
            val usedId = user?.uid
            
            val username = binding.username.text.toString()
            
            val email = binding.email.text.toString()
            
            db.collection("users").document(usedId.toString())
                .update(
                    mapOf(
                        "name" to username,
                        "email" to email
                    )
                ).addOnCompleteListener() {
                    binding.btnEdit.isEnabled = true
                    
                    showDialogSuccess()
        
                    setUser()
        
                    binding.isEdit.visibility = android.view.View.GONE
                    binding.isView.visibility = android.view.View.VISIBLE
        
                    binding.usernameLayout.isClickable = false
                    binding.usernameLayout.isEnabled = false
                }.addOnFailureListener { e ->
                    showDialogError()
                }
        }
    }
    
    private fun showDialogSuccess() {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setTitle("Success")
        dialog.setMessage("Profile updated successfully")
        dialog.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }
    
    private fun showDialogError() {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setTitle("Error")
        dialog.setMessage("Profile failed to update")
        dialog.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }
}