package com.pratik.demoapp.utils

import com.pratik.demoapp.data.model.users.result.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("users")
    fun getUsersRequest(@Query("offset") offset: Int=0, @Query("limit") limit: Int=10): Call<Users>

}