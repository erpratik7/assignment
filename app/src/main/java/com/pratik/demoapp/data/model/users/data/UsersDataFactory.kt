package com.pratik.demoapp.data.model.users.data

import androidx.paging.DataSource
import com.pratik.demoapp.data.model.users.result.User

class UsersDataFactory: DataSource.Factory<Int, User>() {
    private lateinit var postLiveData:UsersDataSource
    override fun create(): DataSource<Int, User> {
        postLiveData= UsersDataSource()
        return postLiveData
    }

    fun refresh() {
        postLiveData.invalidate();
    }
}