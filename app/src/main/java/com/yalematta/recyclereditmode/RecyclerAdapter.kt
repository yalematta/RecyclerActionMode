package com.yalematta.recyclereditmode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val recyclerList: MutableList<Contact>) : RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentItem = recyclerList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.subTitleView.text = currentItem.subTitle
        holder.titleView.text = currentItem.title
    }

    override fun getItemCount(): Int = recyclerList.size
}