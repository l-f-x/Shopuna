package com.lfx.shopuna.ui.base.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lfx.shopuna.R
import com.lfx.shopuna.data.model.ProductGetCartOutputModel
import com.lfx.shopuna.utils.Helper

class CartRecyclerViewAdapter(private val dataSet: List<ProductGetCartOutputModel>, private val token: String) :
    RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgv: ImageView
        val name: TextView
        val desc: TextView
        val weight: TextView
        val price: TextView
        val counter: TextView


        init {
            // Define click listener for the ViewHolder's View.
            imgv = view.findViewById(R.id.imgvCart)
            name = view.findViewById(R.id.tvNameCart)
            desc = view.findViewById(R.id.tvDescCart)
            weight = view.findViewById(R.id.tvWeightCart)
            price = view.findViewById(R.id.tvPriceCart)
            counter = view.findViewById(R.id.tvCounterCart)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cart_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.imgv.load(Helper().getImageSourceLinkById(dataSet[position].product.id, token))
        viewHolder.name.text = dataSet[position].product.product_name
        viewHolder.desc.text = dataSet[position].product.product_description
        viewHolder.weight.text = dataSet[position].product.product_weight.toString()
        viewHolder.weight.text = dataSet[position].count.toString()
        if(dataSet[position].product.has_sale) {
            viewHolder.price.text = dataSet[position].product.price_on_sale.toString()
        }
        else {
            viewHolder.price.text = dataSet[position].product.product_price.toString()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}