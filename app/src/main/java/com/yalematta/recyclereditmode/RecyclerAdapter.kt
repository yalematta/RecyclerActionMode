package com.yalematta.recyclereditmode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerAdapter(private val recyclerList: List<RecyclerItem>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subTitleView: TextView = itemView.subTitleView
        val imageView: ImageView = itemView.imageView
        val titleView: TextView = itemView.titleView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = recyclerList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.subTitleView.text = currentItem.subTitle
        holder.titleView.text = currentItem.title
    }

    override fun getItemCount(): Int = recyclerList.size
}