package com.yalematta.recyclereditmode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerList = generateDummyList(500)
        recyclerView.adapter = RecyclerAdapter(recyclerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<RecyclerItem> {
        val list = ArrayList<RecyclerItem>();

        for (i in 0 until size) {
            val drawable = R.drawable.ic_account
            val item = RecyclerItem(drawable, "Title $i", "Subtitle")
            list += item
        }

        return list
    }
}
