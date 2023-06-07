package com.github.user.soilitouraplication.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.user.soilitouraplication.api.Campaign
import com.github.user.soilitouraplication.databinding.CampaignItemBinding

class CampaignAdapter : RecyclerView.Adapter<CampaignAdapter.ViewHolder>() {

    private val list = ArrayList<Campaign>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(campaigns: List<Campaign>) {
        list.clear()
        list.addAll(campaigns)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: CampaignItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(campaign: Campaign) {
            binding.apply {
                val width = dpToPx(itemView.context, 116)
                val height = dpToPx(itemView.context, 146)


                Glide.with(itemView)
                    .load(campaign.image)
                    .apply(RequestOptions().override(width, height).centerCrop()) // Add .centerCrop() here
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivccampaign) // Make sure this ImageView is properly initialized

                tvtitle.text = campaign.name
            }
        }

        private fun dpToPx(context: Context, dp: Int): Int {
            val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
            )
            return px.toInt()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            CampaignItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
