package com.github.user.soilit.ui.dashboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.user.soilit.api.Message
import com.github.user.soilit.databinding.FragmentProfileBinding
import com.github.user.soilit.ui.dashboard.Adapter.Message.MessagingAdapter
import com.github.user.soilit.utils.BotResponse
import com.github.user.soilit.utils.Constants.OPEN_GOOGLE
import com.github.user.soilit.utils.Constants.OPEN_SEARCH
import com.github.user.soilit.utils.Constants.RECEIVE_ID
import com.github.user.soilit.utils.Constants.SEND_ID
import com.github.user.soilit.utils.Time
import kotlinx.coroutines.*

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val TAG = "ProfileFragment"
    private var messagesList = mutableListOf<Message>()
    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Fawwaz", "Viony", "Rama", "Adiva", "Naily")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customBotMessage("Halo ! dengan saya ${botList[random]}, Adakah yang bisa saya bantu ?")
    }

    private fun clickEvents() {
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(context)
    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = binding.etMessage.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            messagesList.add(Message(message, SEND_ID, timeStamp))
            binding.etMessage.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)

                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}