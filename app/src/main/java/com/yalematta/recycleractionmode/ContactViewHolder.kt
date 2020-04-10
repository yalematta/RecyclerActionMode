package com.yalematta.recycleractionmode

import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactViewHolder(itemView: View, private val listener: ContactClickListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    val cardView: CardView = itemView.cardView
    val checkBox: CheckBox = itemView.checkbox
    val imageView: ImageView = itemView.imageView
    val titleView: EditText = itemView.titleEditView
    val subTitleView: TextView = itemView.subTitleView

    init {
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