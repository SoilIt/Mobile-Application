package com.github.user.soilit.ui.main

import SearchFragment
import SettingsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.user.soilit.R
import com.github.user.soilit.databinding.ActivityMainBinding
import com.github.user.soilit.ui.dashboard.HomeFragment
import com.github.user.soilit.ui.dashboard.ProfileFragment

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
        
        // handle bottom nav item selection
        binding.btnAddPhoto.setOnClickListener {
        
        }
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment())
                        .commit()
                    true
                }
                R.id.history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ProfileFragment())
                        .commit()
                    true
                }
                R.id.setting -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SettingsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
