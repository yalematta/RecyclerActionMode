package com.yalematta.recyclereditmode

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.contacts)

        val recyclerList = generateDummyList(100)

        recyclerView.adapter = RecyclerAdapter(recyclerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<RecyclerItem> {
        val list = ArrayList<RecyclerItem>();

        for (i in 0 until size) {
            val drawable = R.drawable.ic_account
            val item = RecyclerItem(drawable, "Person $i", "Details")
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
        Toast.makeText(this, "Opening Edit Mode...", Toast.LENGTH_SHORT).show()
    }

}
