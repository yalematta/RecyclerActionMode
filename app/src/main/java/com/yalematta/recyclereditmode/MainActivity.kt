package com.yalematta.recyclereditmode

import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), SelectionInterface {

    companion object {
        var TAG: String = MainActivity::class.java.simpleName
    }

    var actionMode: ActionMode? = null
    var adapter: ContactAdapter? = null
    private lateinit var recyclerList: MutableList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.contacts)

        recyclerList = generateDummyList(100)
        adapter = ContactAdapter(this, recyclerList, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): MutableList<Contact> {
        val list = ArrayList<Contact>();

        for (i in 0 until size) {
            val drawable = R.drawable.ic_account
            val item = Contact(i.toString(), drawable, "Person $i", "Details")
            list += item
        }

        return list
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit -> {
                openEditMode()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openEditMode() {

        if (actionMode == null)
            actionMode = startActionMode(ActionModeCallback(), ActionMode.TYPE_PRIMARY)

    }

    inner class ActionModeCallback : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.delete -> {
                    adapter?.deleteSelectedIds()
                    actionMode?.title = "" //remove item count from action mode.
                    actionMode?.finish()
                    return true
                }
            }
            return false
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = mode?.menuInflater
            inflater?.inflate(R.menu.action_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            menu?.findItem(R.id.delete)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            Log.d(TAG, "onDestroyActionMode Called")
            adapter?.selectedIds?.clear()
            adapter?.notifyDataSetChanged()
            actionMode = null
        }
    }

    override fun setSelectedNumber(size: Int) {
        if (actionMode == null) actionMode = startActionMode(ActionModeCallback())
        if (size > 0) actionMode?.title = "$size Selected"
        else actionMode?.finish()
    }

}
