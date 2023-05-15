package com.github.user.soilit.ui.main

import SearchFragment
import SettingsFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.user.soilit.R
import com.github.user.soilit.databinding.ActivityMainBinding
import com.github.user.soilit.ui.addstories.AddStories
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
            val addStories = Intent(this@MainActivity, AddStories::class.java)
            startActivity(addStories)
        }
        binding.bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment())
                        .commit()
                    true
                }
                R.id.person -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ProfileFragment())
                        .commit()
                    true
                }
                R.id.Search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment ())
                        .commit()
                    true
                }
                R.id.settings -> {
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
