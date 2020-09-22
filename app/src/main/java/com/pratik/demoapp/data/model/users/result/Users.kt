package com.pratik.demoapp.data.model.users.result

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Users {

    @SerializedName("status")
    @Expose
    var status = false

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    override fun toString(): String {
        return StringBuilder().append("status", status).append("message", message)
            .append("data", data).toString()
    }

}