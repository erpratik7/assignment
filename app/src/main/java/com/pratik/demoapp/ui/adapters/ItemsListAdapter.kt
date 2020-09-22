package com.pratik.demoapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pratik.demoapp.R
import com.squareup.picasso.Picasso

class ItemsListAdapter(private val mContext: Context, private val usersList: List<String>) :
    RecyclerView.Adapter<ItemsListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_image_item, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, i: Int) {
        if (i != RecyclerView.NO_POSITION && i < usersList.size) {
            holder.ivItem?.tag = "item:${holder.adapterPosition}"

            val logoUrl = usersList[i]
            if (logoUrl.isNotBlank()) {
                Picasso.get().load(logoUrl).placeholder(R.drawable.loading)
                    .error(R.drawable.error_icon)
                    .into(holder.ivItem)
            } else Picasso.get().load(R.drawable.no_image)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var ivItem: ImageView? = v.findViewById(R.id.ivItem)
    }

}