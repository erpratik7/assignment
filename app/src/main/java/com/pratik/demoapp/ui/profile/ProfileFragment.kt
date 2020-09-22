package com.pratik.demoapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pratik.demoapp.R
import com.pratik.demoapp.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.email.observe(viewLifecycleOwner, {
            tvUserName?.text = it
        })
        btnLogout?.setOnClickListener {
            profileViewModel.logout()
        }

        profileViewModel.isLoggedIn.observe(viewLifecycleOwner, {
            if (!it) {
                startActivity(Intent(activity, LoginActivity::class.java))
                activity?.finish()
            }
        })
    }
}