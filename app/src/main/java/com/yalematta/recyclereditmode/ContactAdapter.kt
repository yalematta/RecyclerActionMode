package com.yalematta.recyclereditmode

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val context: Context,
    private val contactList: MutableList<Contact>,
    private val selectionInterface: SelectionInterface
) :
    RecyclerView.Adapter<ContactViewHolder>(), ContactClickListener {

    val selectedIds: MutableList<String> = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.subTitleView.text = currentItem.subTitle
        holder.titleView.text = currentItem.title

        val id = contactList[position].id

        if (selectedIds.contains(id)) {
            //if item is selected then,set foreground color of FrameLayout.
            holder.titleView.foreground =
                ColorDrawable(ContextCompat.getColor(context, R.color.colorPrimary))
        } else {
            //else remove selected item color.
            holder.titleView.foreground =
                ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent))
        }
    }

    override fun getItemCount(): Int = contactList.size

    override fun onClick(index: Int) {
        if (MainActivity.isMultiSelectionOn)
            addToSelectedIds(index)
    }

    private fun addToSelectedIds(index: Int) {
        val id = contactList[index].id
        if (selectedIds.contains(id))
            selectedIds.remove(id)
        else
            selectedIds.add(id)

        notifyItemChanged(index)
        selectionInterface.setSelectedNumber(selectedIds.size)
    }

    fun deleteSelectedIds() {
        if (selectedIds.size < 1) return
        val selectedIdIteration = selectedIds.listIterator();

        while (selectedIdIteration.hasNext()) {
            val selectedItemID = selectedIdIteration.next()
            Log.d(MainActivity.TAG, "The ID is $selectedItemID")
            var indexOfModelList = 0
            val modelListIteration: MutableListIterator<Contact> = contactList.listIterator();
            while (modelListIteration.hasNext()) {
                val model = modelListIteration.next()
                if (selectedItemID == model.id) {
                    modelListIteration.remove()
                    selectedIdIteration.remove()
                    notifyItemRemoved(indexOfModelList)
                }
                indexOfModelList++
            }

            MainActivity.isMultiSelectionOn = false
        }
    }
}