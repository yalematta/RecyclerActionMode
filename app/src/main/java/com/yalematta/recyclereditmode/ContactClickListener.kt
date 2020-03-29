package com.yalematta.recyclereditmode

interface ContactClickListener {
    fun onClick(index : Int)
    fun onCheckedChangedListener(index : Int, isChecked: Boolean)
}