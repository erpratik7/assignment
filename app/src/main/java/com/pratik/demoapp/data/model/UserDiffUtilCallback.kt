package com.pratik.demoapp.data.model

import androidx.recyclerview.widget.DiffUtil
import com.pratik.demoapp.data.model.users.result.User

class UserDiffUtilCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name && oldItem.image == newItem.image&&oldItem.items?.size==newItem.items?.size
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name && oldItem.image == newItem.image&&oldItem.items?.size==newItem.items?.size

    }

}