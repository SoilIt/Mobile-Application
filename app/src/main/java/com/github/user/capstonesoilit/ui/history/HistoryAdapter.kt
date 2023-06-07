package com.github.user.capstonesoilit.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.user.capstonesoilit.api.History
import com.github.user.capstonesoilit.databinding.HistoryItemBinding
import com.github.user.capstonesoilit.utils.DateUtils

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var historyList: List<History> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(historyList: List<History>) {
        this.historyList = historyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historyItem = historyList[position]
        holder.bind(historyItem)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<History>) {
        historyList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(historyItem: History) {
            binding.apply {
                tvSoil.text = historyItem.soil_type
                tvDateCreated.text = DateUtils.formatDateTime(historyItem.created_at)
                tvMoisture.text = historyItem.soil_moisture.toString()
                tvSoilCondition.text = historyItem.soil_condition
            }
            // Bind data ke tampilan ViewHolder sesuai kebutuhan
            // Contoh: binding.textView.text = historyItem.nama
        }
    }
}
