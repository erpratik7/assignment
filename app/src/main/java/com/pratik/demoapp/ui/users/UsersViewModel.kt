package com.pratik.demoapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pratik.demoapp.data.model.users.data.UsersDataFactory
import com.pratik.demoapp.data.model.users.result.User

class UsersViewModel : ViewModel() {
    private val factory = UsersDataFactory()
    val usersList: LiveData<PagedList<User>> = LivePagedListBuilder(
        factory,
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .setPrefetchDistance(5)
            .build()
    ).build()

    fun refresh() {
        factory.refresh()

    }
}