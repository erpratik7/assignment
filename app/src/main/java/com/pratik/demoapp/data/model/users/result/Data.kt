package com.pratik.demoapp.data.model.users.result

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("users")
    @Expose
    var users: List<User>? = null

    @SerializedName("has_more")
    @Expose
    var hasMore = false
    override fun toString(): String {
        return StringBuilder().append("users", users).append("hasMore", hasMore).toString()
    }
}