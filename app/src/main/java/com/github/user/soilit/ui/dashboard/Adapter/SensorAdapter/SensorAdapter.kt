package com.github.user.soilit.ui.dashboard.Adapter.SensorAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.user.soilit.R
import com.github.user.soilit.api.User_sensor

class SensorAdapter (val postModel: MutableList<User_sensor>): RecyclerView.Adapter<PostViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.itemView(postModel[position])
    }

    override fun getItemCount(): Int {
        return 5
    }
}

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_light)
    private val tvTitle2: TextView = itemView.findViewById(R.id.tv_item_time)
    fun itemView(postModel: User_sensor){



        //old original
       // tvTitle.text = postModel.id
        tvTitle2.text = postModel.intensity


    }


}