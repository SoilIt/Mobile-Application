package com.soilit.app.soilit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soilit.app.soilit.databinding.FragmentProfileBinding
import kotlinx.coroutines.*

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
            }
        }
    }
}