package com.yalematta.recyclereditmode

import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactViewHolder(itemView: View, private val listener: ContactClickListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    val cardView: CardView
    val checkBox: CheckBox
    val titleView: TextView
    val imageView: ImageView
    val subTitleView: TextView

    init {
        cardView = itemView.cardView
        checkBox = itemView.checkbox
        titleView = itemView.titleView
        imageView = itemView.imageView
        subTitleView = itemView.subTitleView

        cardView.setOnClickListener(this)
        checkBox.setOnCheckedChangeListener(this)

    }

    override fun onClick(v: View?) {
        listener.onClick(adapterPosition)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        listener.onCheckedChangedListener(adapterPosition, isChecked)
    }
}