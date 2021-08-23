package com.lfx.shopuna.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import coil.load
import com.lfx.shopuna.R
import com.lfx.shopuna.databinding.ActivityProductInfoBinding
import com.lfx.shopuna.databinding.ActivityRegistrationBinding
import com.lfx.shopuna.utils.Helper

private lateinit var binding: ActivityProductInfoBinding
class ProductInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        binding.tvDescProductInfo.movementMethod = ScrollingMovementMethod.getInstance()
        binding.tvNameProductInfo.text = bundle?.getString("name")
        binding.tvDescProductInfo.text = bundle?.getString("description")
        binding.tvPriceProductInfo.text = bundle?.getString("price")
        binding.tvWeightProductInfo.text = bundle?.getString("weight")
        binding.imgvProductInfo.load(Helper().getImageSourceLinkById(bundle?.getInt("id"), bundle?.getString("token")))
    }

    fun minus(view: View) {
        var count: Int = binding.tvCounterProductInfo.text.toString().toInt()
        if(count!=1){
            count = count-1
            binding.tvCounterProductInfo.text = "$count"
        }
    }
    fun plus(view: View) {
        var count: Int = binding.tvCounterProductInfo.text.toString().toInt()
        count = count+1
        binding.tvCounterProductInfo.text = "$count"
    }
    fun add(view: View) {}
}