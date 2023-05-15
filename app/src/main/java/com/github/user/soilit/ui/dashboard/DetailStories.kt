package com.github.user.soilit.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.github.user.soilit.R
import com.github.user.soilit.databinding.ActivityDetailStoriesBinding

class DetailStoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.title = intent.getStringExtra("list_name")
        binding.ivDetailName.text = intent.getStringExtra("list_name")
        Glide.with(this)
            .load(intent.getStringExtra("list_image"))
            .error(R.drawable.ic_launcher_background)
            .into(binding.ivDetailPhoto)
        binding.ivDetailDescription.text = intent.getStringExtra("list_description")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}