package com.github.user.soilit.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.user.soilit.R
import com.github.user.soilit.db.Stories

class StoriesAdapter(
    private val list: MutableList<Stories>, private val listener: OnAdapterListener
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name : TextView = view.findViewById(R.id.tv_item_name)
        val photo: ImageView = view.findViewById(R.id.iv_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stories_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        Glide.with(holder.photo)
            .load(item.photoUrl)
            .error(R.drawable.ic_launcher_background)
            .into(holder.photo)

        holder.itemView.setOnClickListener {
            listener.onClick(item)

        }
    }

    interface OnAdapterListener{
        fun onClick(list: Stories)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}