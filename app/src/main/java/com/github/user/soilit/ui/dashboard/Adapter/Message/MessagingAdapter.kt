package com.github.user.soilit.ui.dashboard.Adapter.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.user.soilit.R
import com.github.user.soilit.api.Message
import com.github.user.soilit.databinding.MessageItemBinding
import com.github.user.soilit.utils.Constants.RECEIVE_ID
import com.github.user.soilit.utils.Constants.SEND_ID




class MessagingAdapter : RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    private var messagesList = mutableListOf<Message>()
    private lateinit var context: Context

    inner class MessageViewHolder(private val binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            when (message.id) {
                SEND_ID -> {
                    binding.tvMessage.text = message.message
                    binding.tvMessage.visibility = View.VISIBLE
                    binding.tvBotMessage.visibility = View.GONE
                }
                RECEIVE_ID -> {
                    binding.tvBotMessage.text = message.message
                    binding.tvBotMessage.visibility = View.VISIBLE
                    binding.tvMessage.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        context = parent.context
        val binding = MessageItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]
        holder.bind(currentMessage)
    }

    fun insertMessage(message: Message) {
        messagesList.add(message)
        notifyItemInserted(messagesList.size - 1)
    }

    companion object {
        private const val TAG = "MessagingAdapter"
    }

}
