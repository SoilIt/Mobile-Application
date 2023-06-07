package com.github.user.capstonesoilit.ui.fullcampaign

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.user.capstonesoilit.api.Campaign
import com.github.user.capstonesoilit.databinding.FullCampaignItemBinding

class FullCampaignAdapter : RecyclerView.Adapter<FullCampaignAdapter.ViewHolder>() {

    private val list = ArrayList<Campaign>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(campaigns: List<Campaign>) {
        list.clear()
        list.addAll(campaigns)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: FullCampaignItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(campaign: Campaign) {
            binding.apply {
                val width = dpToPx(itemView.context, 120)
                val height = dpToPx(itemView.context, 120)


                Glide.with(itemView)
                    .load(campaign.image)
                    .apply(RequestOptions().override(width, height).centerCrop()) // Add .centerCrop() here
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivcampaign) // Make sure this ImageView is properly initialized

                //tvcampaign.text = campaign.name
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
            FullCampaignItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
