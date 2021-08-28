package com.lfx.shopuna.ui.base.onClickInterface

import android.widget.Adapter
import android.widget.ImageView
import com.lfx.shopuna.ui.base.controllers.CartRecyclerViewAdapter

interface CartOnClickListener {
    fun onClicked(id: Int, name: String, description: String, weight: String, price: String, token: String)
    fun onPlusCliked(id: Int)
    fun onMinusCliked(id: Int,count: Int)
    fun onDeleteCliked(id: Int,count: Int)
}