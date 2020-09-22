package com.pratik.demoapp.data.model.users.result

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("items")
    @Expose
    var items: List<String>? = null
    var hasMore = false
    override fun toString(): String {
        return StringBuilder().append("name", name).append("image", image)
            .append("items", items).toString()
    }

}