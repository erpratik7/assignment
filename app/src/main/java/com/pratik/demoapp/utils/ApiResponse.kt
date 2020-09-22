package com.pratik.demoapp.utils

import retrofit2.Response

interface ApiResponse<T> {
    fun onResponse(requestCode: Int, response: Response<T>, callback: Any)
    fun onFailure(requestCode: Int, errorMessage: String)
}