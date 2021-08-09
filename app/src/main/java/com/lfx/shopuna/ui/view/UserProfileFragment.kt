package com.lfx.shopuna.ui.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.lfx.shopuna.data.api.RetrofitBuilder.BASE_URL
import com.lfx.shopuna.databinding.FragmentUserProfileBinding
import com.lfx.shopuna.ui.viewmodel.UserViewModel
import com.lfx.shopuna.utils.NetworkStatus
import com.lfx.shopuna.utils.TOKEN
import com.lfx.shopuna.utils.USER_DATA_FILE_NAME


private var _binding: FragmentUserProfileBinding? = null
private val binding get() = _binding!!
class UserProfileFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.getSelfInfo().observe(viewLifecycleOwner, {
            when (it.status) {
                NetworkStatus.SUCCESS -> {
                    binding.tvNameProfile.text = it.data?.real_name
                    binding.tvBalanceProfile.text = it.data?.balance.toString()
                    binding.tvEmailProfile.text = it.data?.login
                    //TODO убрать говнокод и создать билдер
                    val test =
                        "${BASE_URL}users/selected_avatar/${it.data?.id}?token=${viewModel.token.value}"
                    binding.imgvUserProfile.load(test)
                }
                NetworkStatus.LOADING -> {
                }
                NetworkStatus.ERROR -> {
                }
            }

        })


        binding.btnLogout.setOnClickListener {
            with(activity?.getSharedPreferences(USER_DATA_FILE_NAME, MODE_PRIVATE)?.edit()) {
                this?.putString(TOKEN, null)
                this?.apply()
            }
            val intent = Intent(activity?.applicationContext, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        binding.btnChangeUserProfile.setOnClickListener {
            val intent = Intent(activity?.applicationContext, ChangeUserDataActivity::class.java)
            startActivity(intent)
        }
        return view

    }


}