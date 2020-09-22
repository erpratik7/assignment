package com.pratik.demoapp.data.model.users.data

import android.util.Log
import androidx.paging.PositionalDataSource
import com.pratik.demoapp.data.model.users.result.User
import com.pratik.demoapp.data.model.users.result.Users
import com.pratik.demoapp.utils.ApiInterface
import com.pratik.demoapp.utils.ApiManager
import com.pratik.demoapp.utils.ApiResponse
import com.pratik.demoapp.utils.RetrofitClient
import retrofit2.Response
import java.net.HttpURLConnection

class UsersDataSource : PositionalDataSource<User>(), ApiResponse<Users> {
    private var apiInterface: ApiInterface? = RetrofitClient.getRetrofitClient()
    private val CODE_I = 1000
    private val CODE_R = 1001
    private var hasMoreData = true
    private var offset = 0


    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<User>) {
        hasMoreData = true
        val call =
            apiInterface?.getUsersRequest(params.requestedStartPosition, params.pageSize)
        if (call != null) {
            ApiManager(this).makeRequest(
                call,
                CODE_I,
                callback
            )
        }
    }


    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<User>) {
        if (hasMoreData) {
            offset += 10
            Log.e("loadRange", "${offset}, sp:${params.startPosition}, ls:${params.loadSize}")
            val call = apiInterface?.getUsersRequest(offset = offset)
            if (call != null) {
                ApiManager(this).makeRequest(
                    call,
                    CODE_R,
                    callback
                )
            }
        }
    }

    override fun onResponse(requestCode: Int, response: Response<Users>, callback: Any) {
        if (response.code() == HttpURLConnection.HTTP_OK && response.body()?.data?.users != null) {
            val values = response.body()?.data?.users!!
            hasMoreData = response.body()?.data?.hasMore ?: true
            values[values.size - 1].hasMore = hasMoreData
            if (requestCode == CODE_I) {
                Log.e("callback", "I")
                (callback as? LoadInitialCallback<User>)?.onResult(
                    values,
                    offset
                )
            } else if (requestCode == CODE_R) {
                (callback as? LoadRangeCallback<User>)?.onResult(
                    values
                )
            }
        }
    }

    override fun onFailure(requestCode: Int, errorMessage: String) {

    }

}