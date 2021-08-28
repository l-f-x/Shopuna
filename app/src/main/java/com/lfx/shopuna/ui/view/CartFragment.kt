package com.lfx.shopuna.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lfx.shopuna.R
import com.lfx.shopuna.databinding.FragmentCartBinding
import com.lfx.shopuna.ui.base.controllers.CartRecyclerViewAdapter
import com.lfx.shopuna.ui.base.onClickInterface.CartOnClickListener
import com.lfx.shopuna.ui.viewmodel.ProductViewModel
import com.lfx.shopuna.utils.NetworkStatus

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private var _binding: FragmentCartBinding? = null
private val binding get() = _binding!!
lateinit var recyclerView : RecyclerView
class CardFragment : Fragment() {

    private val viewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView= binding.rvCart
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        update()
        return view
    }

    fun update(){
        viewModel.cart().observe(viewLifecycleOwner){
            when(it.status){
                NetworkStatus.SUCCESS -> {
                    recyclerView.adapter = CartRecyclerViewAdapter(it?.data!!, viewModel.token.value!!, object: CartOnClickListener{
                        override fun onClicked(
                            id: Int,
                            name: String,
                            description: String,
                            weight: String,
                            price: String,
                            token: String
                        ) {
                            val intent = Intent(activity?.applicationContext, ProductInfoActivity::class.java)
                            val args = Bundle()
                            args.putString("token", token)
                            args.putInt("id", id)
                            args.putString("name", name)
                            args.putString("description", description)
                            args.putString("weight", weight)
                            args.putString("price", price)
                            intent.putExtras(args)
                            startActivity(intent)
                        }

                        override fun onPlusCliked(id: Int) {

                            viewModel.add_to_cart(id, 1).observe(viewLifecycleOwner){
                                when(it.status){
                                    NetworkStatus.SUCCESS->{
                                        update()
                                    }
                                    NetworkStatus.ERROR -> Toast.makeText(activity?.applicationContext,"произошла ошибка на сервере", Toast.LENGTH_SHORT).show()
                                    NetworkStatus.LOADING -> {
                                        // dialogHelper.showLoadingDialog("Загрузка")
                                    }
                                }
                            }
                        }

                        override fun onMinusCliked(id: Int, count: Int) {
                            if(count!=1){
                                viewModel.add_to_cart(id, -1).observe(viewLifecycleOwner){
                                    when(it.status){
                                        NetworkStatus.SUCCESS->{
                                            update()
                                        }
                                        NetworkStatus.ERROR -> Toast.makeText(activity?.applicationContext,"произошла ошибка на сервере", Toast.LENGTH_SHORT).show()
                                        NetworkStatus.LOADING -> {
                                            // dialogHelper.showLoadingDialog("Загрузка")
                                        }
                                    }
                                }
                            }
                        }

                        override fun onDeleteCliked(id: Int, count: Int) {
                                viewModel.add_to_cart(id, -count).observe(viewLifecycleOwner){
                                    when(it.status){
                                        NetworkStatus.SUCCESS->{
                                            update()
                                        }
                                        NetworkStatus.ERROR -> Toast.makeText(activity?.applicationContext,"произошла ошибка на сервере", Toast.LENGTH_SHORT).show()
                                        NetworkStatus.LOADING -> {
                                            // dialogHelper.showLoadingDialog("Загрузка")
                                        }
                                    }
                                }
                        }

                    })
                    binding.shimmerCart.stopShimmer()
                    binding.shimmerCart.hideShimmer()
                    binding.shimmerCart.visibility = View.GONE
                    binding.rvCart.visibility = View.VISIBLE

                }
                NetworkStatus.ERROR -> {
                   // Snackbar.make(view,"Мы приносим свои извинения, сервер не работает. Мы постараемся исправить это в ближайшее время",Snackbar.LENGTH_SHORT).show()
                }
                NetworkStatus.LOADING -> {

                }
            }
        }
    }
}