package com.yalematta.recyclereditmode

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleView: TextView
    val imageView: ImageView
    val subTitleView: TextView

    init {
        titleView = itemView.titleView
        imageView = itemView.imageView
        subTitleView = itemView.subTitleView
    }
}