package com.lfx.shopuna.ui.base.onClickInterface

import android.widget.ImageView

interface CartOnClickListener {
    fun onClicked(id: Int, name: String, description: String, weight: String, price: String, token: String)
}