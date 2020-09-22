package com.pratik.demoapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratik.demoapp.R
import com.pratik.demoapp.ui.adapters.UsersListAdapter
import com.pratik.demoapp.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_users.*


class UsersFragment : Fragment() {
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userListAdapter = UsersListAdapter(activity)
        rvUserList?.adapter = userListAdapter
        val llm = LinearLayoutManager(context)
        rvUserList?.layoutManager = llm
        swipeContainer?.setOnRefreshListener {
            usersViewModel.refresh()
        }
        usersViewModel.usersList.observe(viewLifecycleOwner, { list ->
            userListAdapter.submitList(list)
            swipeContainer?.isRefreshing = false
        })
    }
}