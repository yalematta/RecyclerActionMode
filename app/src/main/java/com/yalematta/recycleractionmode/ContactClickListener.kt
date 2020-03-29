package com.yalematta.recycleractionmode

interface ContactClickListener {
    fun onClick(index : Int)
    fun onCheckedChangedListener(index : Int, isChecked: Boolean)
}