package com.yalematta.recycleractionmode

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val context: Context,
    private val contactList: MutableList<Contact>,
    private val selectionInterface: SelectionInterface) :
    RecyclerView.Adapter<ContactViewHolder>(), ContactClickListener {

    var onBind: Boolean = false
    val selectedIds: MutableList<String> = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        onBind = true
        val currentItem = contactList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.subTitleView.text = currentItem.subTitle
        holder.titleView.text = currentItem.title

        val id = contactList[position].id

        if (MainActivity.isMultiSelectionOn)
            holder.checkBox.visibility = View.VISIBLE
        else
            holder.checkBox.visibility = View.GONE

        holder.checkBox.isChecked = selectedIds.contains(id)
        onBind = false
    }

    override fun getItemCount(): Int = contactList.size

    override fun onClick(index: Int) {
        if (MainActivity.isMultiSelectionOn)
            addToSelectedIds(index)
    }

    override fun onCheckedChangedListener(index: Int, isChecked: Boolean) {
        if (MainActivity.isMultiSelectionOn)
            addToSelectedIds(index)
    }

    private fun addToSelectedIds(index: Int) {
        if (!onBind) {
            val id = contactList[index].id
            if (selectedIds.contains(id))
                selectedIds.remove(id)
            else
                selectedIds.add(id)

            notifyItemChanged(index)
            selectionInterface.setSelectedNumber(selectedIds.size)
        }
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