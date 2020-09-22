package com.pratik.demoapp.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager<T>(val apiResponse: ApiResponse<T>) {

    fun makeRequest(call: Call<T> , requestCode: Int,callback:Any) {
        call.enqueue(object: Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                apiResponse.onResponse(requestCode, response,callback)
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                apiResponse.onFailure(requestCode, throwable.message.toString())
            }
        })
    }
}