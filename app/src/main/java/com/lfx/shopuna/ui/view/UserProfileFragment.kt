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
import com.lfx.shopuna.utils.Helper
import com.lfx.shopuna.utils.NetworkStatus
import com.lfx.shopuna.utils.TOKEN
import com.lfx.shopuna.utils.USER_DATA_FILE_NAME
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


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
                    binding.imgvUserProfile.load(Helper().getImageSourceLinkById(it.data?.id,viewModel.token.value))
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
    fun plural_days(n : Int): String{
        var p =0
        val days:Array<String> = arrayOf("день", "дня", "дней")

        if(n % 10 == 1 && n % 100 != 11)
            p = 0
        else if (2 <= n % 10 && n%10 <= 4 && (n % 100 < 10 || n % 100 >= 20))
            p = 1
        else
            p = 2

        return "$n ${days[p]}"
    }


}