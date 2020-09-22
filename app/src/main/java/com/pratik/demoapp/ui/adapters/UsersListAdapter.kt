package com.pratik.demoapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.pratik.demoapp.R
import com.pratik.demoapp.data.model.UserDiffUtilCallback
import com.pratik.demoapp.data.model.users.result.User
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class UsersListAdapter(private val mContext: Context?) :
    PagedListAdapter<User, UsersListAdapter.MyViewHolder>(UserDiffUtilCallback()) {

    private val TYPE_NORMAL = 1
    private val TYPE_LOADER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return when (viewType) {
            TYPE_NORMAL -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user_rv_item, parent, false)
                MyViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.loading_rv_item, parent, false)
                MyViewHolder(v)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position != 0 && getItem(position - 1)?.hasMore != false && position == itemCount - 1) {
            TYPE_LOADER
        } else {
            TYPE_NORMAL
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_NORMAL -> {
                val user = getItem(position) ?: return

                holder.rvItems?.tag = "user:${holder.adapterPosition}"
                holder.userName?.text = user.name
                holder.rvItems?.adapter = user.items?.let {
                    mContext?.let { context ->
                        ItemsListAdapter(context, it)
                    }
                }
                val manager = GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false)
                val isOdd = (user.items?.size ?: 0) % 2 != 0
                manager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (isOdd && position == 0)
                            2
                        else 1
                    }
                }
                holder.rvItems?.layoutManager = manager

                val logoUrl = user.image
                if (logoUrl?.isNotEmpty() == true) {
                    val iv = holder.userImage
                    iv?.visibility = View.VISIBLE
                    Picasso.get().load(logoUrl).placeholder(R.drawable.loading)
                        .error(R.drawable.error_icon)
                        .into(iv)
                } else
                    Picasso.get().load(R.drawable.no_image).noFade()
                        .into(holder.userImage)
            }

            TYPE_LOADER -> {

            }
        }
    }

    override fun getItemCount(): Int {
        val size = super.getItemCount()
        return if (size != 0 && getItem(size - 1)?.hasMore != false)
            size + 1
        else size
    }

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var userName: TextView? = v.findViewById(R.id.userName)
        var rvItems: RecyclerView? = v.findViewById(R.id.rvItems)
        var userImage: CircleImageView? = v.findViewById(R.id.userImage)
    }

}