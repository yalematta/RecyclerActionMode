package com.yalematta.recyclereditmode

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactViewHolder(itemView: View, private val listener: ContactClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val cardView: CardView
    val titleView: TextView
    val imageView: ImageView
    val subTitleView: TextView

    init {
        cardView = itemView.cardView
        titleView = itemView.titleView
        imageView = itemView.imageView
        subTitleView = itemView.subTitleView

        cardView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onClick(adapterPosition)
    }
}