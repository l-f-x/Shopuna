package com.lfx.shopuna.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.ProductHelper
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.data.api.UserHelper
import com.lfx.shopuna.databinding.ActivityProductInfoBinding
import com.lfx.shopuna.databinding.ActivityRegistrationBinding
import com.lfx.shopuna.ui.base.ProductViewModelFactory
import com.lfx.shopuna.ui.base.UserViewModelFactory
import com.lfx.shopuna.ui.viewmodel.ProductViewModel
import com.lfx.shopuna.ui.viewmodel.UserViewModel
import com.lfx.shopuna.utils.Helper
import com.lfx.shopuna.utils.NetworkDialogUtils
import com.lfx.shopuna.utils.NetworkStatus

private lateinit var binding: ActivityProductInfoBinding

@SuppressLint("StaticFieldLeak")
private lateinit var dialogHelper: NetworkDialogUtils
class ProductInfoActivity : AppCompatActivity() {
    lateinit var viewModel: ProductViewModel
    lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialogHelper = NetworkDialogUtils(this, layoutInflater)
        bundle = intent.extras!!
        binding.tvDescProductInfo.movementMethod = ScrollingMovementMethod.getInstance()
        binding.tvNameProductInfo.text = bundle?.getString("name")
        binding.tvDescProductInfo.text = bundle?.getString("description")
        binding.tvPriceProductInfo.text = bundle?.getString("price")
        binding.tvWeightProductInfo.text = bundle?.getString("weight")
        binding.imgvProductInfo.load(Helper().getImageSourceProductLinkById(bundle?.getInt("id"), bundle?.getString("token")))
        val productHelper = ProductHelper(RetrofitBuilder.productService)
        viewModel =
            ViewModelProvider(this, ProductViewModelFactory(productHelper)).get(ProductViewModel::class.java)
        viewModel.token.postValue(bundle?.getString("token"))
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
    fun add(view: View) {
        viewModel.add_to_cart(bundle!!.getInt("id"), binding.tvCounterProductInfo.text.toString().toInt()).observe(this){
            when(it.status){
                NetworkStatus.SUCCESS->{
                    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.cartFrameLayout, CardFragment()).commit()
                }
                NetworkStatus.ERROR -> Toast.makeText(this,"произошла ошибка на сервере", Toast.LENGTH_SHORT).show()
                NetworkStatus.LOADING -> {
                   // dialogHelper.showLoadingDialog("Загрузка")
                }
            }
        }
    }
}