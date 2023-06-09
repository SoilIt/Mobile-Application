package com.github.user.soilitouraplication

import SettingsFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.user.soilitouraplication.databinding.ActivityMainBinding
import com.github.user.soilitouraplication.ui.detection.ClassifierActivity
import com.github.user.soilitouraplication.ui.history.HistoryFragment
import com.github.user.soilitouraplication.ui.home.HomeFragment
import com.github.user.soilitouraplication.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentFragment: Fragment? = null
    private lateinit var homeFragment: HomeFragment
    private lateinit var historyFragment: HistoryFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var profileActivity: ProfileFragment
    
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
                    showFragment(homeFragment)
                    true
                }
                
                R.id.history -> {
                    showFragment(historyFragment)
                    true
                }
                
                R.id.settings -> {
                    showFragment(settingsFragment)
                    true
                }
                
                R.id.profile -> {
                    showFragment(profileActivity)
                    true
                }
                else -> false
            }
        }
        
        // Initialize fragments
        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        settingsFragment = SettingsFragment()
        profileActivity = ProfileFragment()
        
        // Show HomeFragment initially
        showFragment(homeFragment)
    }
    
    private fun showFragment(fragment: Fragment) {
        if (currentFragment == fragment) return
        
        supportFragmentManager.beginTransaction().apply {
            currentFragment?.let { hide(it) }
            if (!fragment.isAdded) {
                add(R.id.container, fragment)
            } else {
                show(fragment)
            }
            commit()
        }
        
        currentFragment = fragment
    }
    
    override fun onPause() {
        super.onPause()
        supportFragmentManager.beginTransaction().apply {
            homeFragment.takeIf { it.isAdded }?.let { hide(it) }
            historyFragment.takeIf { it.isAdded }?.let { hide(it) }
            settingsFragment.takeIf { it.isAdded }?.let { hide(it) }
            profileActivity.takeIf { it.isAdded }?.let { hide(it) }
            commit()
        }
    }
    
    override fun onResume() {
        super.onResume()
        currentFragment?.let { showFragment(it) }
    }
}
