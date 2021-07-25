package com.lfx.shopuna.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfx.shopuna.R
import com.lfx.shopuna.databinding.FragmentUserProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private var _binding: FragmentUserProfileBinding? = null
private val binding get() = _binding!!
class UserProfileFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.btnLogout.setOnClickListener{
            print("aboba")
            val intent = Intent(activity?.applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnChangeUserProfile.setOnClickListener {
            val intent = Intent(activity?.applicationContext,ChangeUserDataActivity::class.java)
            startActivity(intent)
        }
        return view

    }


}