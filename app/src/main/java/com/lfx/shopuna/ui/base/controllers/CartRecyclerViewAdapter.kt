package com.lfx.shopuna.ui.base.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lfx.shopuna.R
import com.lfx.shopuna.data.model.ProductGetCartOutputModel
import com.lfx.shopuna.ui.base.onClickInterface.CartOnClickListener
import com.lfx.shopuna.utils.Helper

class CartRecyclerViewAdapter(private val dataSet: List<ProductGetCartOutputModel>, private val token: String, private val onClickListener: CartOnClickListener) :
    RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgv: ImageView
        val name: TextView
        val desc: TextView
        val weight: TextView
        val price: TextView
        val counter: TextView
        val item: ConstraintLayout
        val plus: ImageView
        val minus: ImageView
        val delete: ImageButton

        init {
            // Define click listener for the ViewHolder's View.
            imgv = view.findViewById(R.id.imgvCart)
            name = view.findViewById(R.id.tvNameCart)
            desc = view.findViewById(R.id.tvDescCart)
            weight = view.findViewById(R.id.tvWeightCart)
            price = view.findViewById(R.id.tvPriceCart)
            counter = view.findViewById(R.id.tvCounterCart)
            item = view.findViewById(R.id.clCartItem)
            plus = view.findViewById(R.id.bntPlusCartItem)
            minus = view.findViewById(R.id.btnMinusCartItem)
            delete = view.findViewById(R.id.deleteCartItem)
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

        val id = dataSet[position].product.id
        val name = dataSet[position].product.product_name
        val desc = dataSet[position].product.product_description
        val weight = "${dataSet[position].product.product_weight.toString()} гр"
        val counter = dataSet[position].count.toString()
        var price = ""
        if(dataSet[position].product.has_sale) {
            price = "${dataSet[position].product.price_on_sale.toString()} Р"
        }
        else {
            price = "${dataSet[position].product.product_price.toString()} Р"
        }

        viewHolder.imgv.load(Helper().getImageSourceProductLinkById(dataSet[position].product.id, token))
        viewHolder.name.text = name
        viewHolder.desc.text = desc
        viewHolder.weight.text = weight
        viewHolder.counter.text = counter
        if(dataSet[position].product.has_sale) {
            viewHolder.price.text = "${dataSet[position].product.price_on_sale.toString()} Р"
        }
        else {
            viewHolder.price.text = "${dataSet[position].product.product_price.toString()} Р"
        }



        viewHolder.item.setOnClickListener{
            onClickListener.onClicked(id, name, desc, weight,price,token)
        }
        viewHolder.plus.setOnClickListener {
            onClickListener.onPlusCliked(id)
        }
        viewHolder.minus.setOnClickListener {
            onClickListener.onMinusCliked(id, counter.toInt())
        }
        viewHolder.delete.setOnClickListener{
            onClickListener.onDeleteCliked(id,counter.toInt())
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updateItem(position: Int){
        this.notifyItemChanged(position)
    }

}