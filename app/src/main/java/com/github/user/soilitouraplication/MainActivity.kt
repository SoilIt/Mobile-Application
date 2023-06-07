package com.github.user.soilitouraplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.user.soilitouraplication.databinding.ActivityMainBinding
import com.github.user.soilitouraplication.ui.detection.ClassifierActivity
import com.github.user.soilitouraplication.ui.history.HistoryFragment
import com.github.user.soilitouraplication.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isHomeFragmentVisible = true
    private lateinit var homeFragment: HomeFragment
    private lateinit var historyFragment: HistoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPhoto.setOnClickListener {
            val intent = Intent(this@MainActivity, ClassifierActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    if (!isHomeFragmentVisible) {
                        showHomeFragment()
                        isHomeFragmentVisible = true
                        binding.btnAddPhoto.isEnabled = true
                    }
                    true
                }
                R.id.person -> {
                    if (isHomeFragmentVisible) {
                        showHistoryFragment()
                        isHomeFragmentVisible = false
                        binding.btnAddPhoto.isEnabled = true
                    }
                    true
                }
                else -> false
            }
        }

        // Initialize fragments
        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()

        // Show HomeFragment initially
        showHomeFragment()
    }

    private fun showHomeFragment() {
        if (!homeFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, homeFragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .show(homeFragment)
                .commit()
        }

        if (historyFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .hide(historyFragment)
                .commit()
        }
    }

    private fun showHistoryFragment() {
        if (!historyFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, historyFragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .show(historyFragment)
                .commit()
        }

        if (homeFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .hide(homeFragment)
                .commit()
        }
    }

    override fun onPause() {
        super.onPause()
        // Menyembunyikan fragment saat aplikasi di-pause
        supportFragmentManager.beginTransaction()
            .hide(homeFragment)
            .hide(historyFragment)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        // Menampilkan kembali fragment saat aplikasi di-resume
        if (isHomeFragmentVisible) {
            showHomeFragment()
        } else {
            showHistoryFragment()
        }
    }
}
