package com.dragon.toolbox.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dragon.toolbox.databinding.ItemThumbnailBinding

class ThumbnailAdapter(val context: Context) :
    RecyclerView.Adapter<ThumbnailAdapter.ThumbnailHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    class ThumbnailHolder(viewbingding: ItemThumbnailBinding) :
        RecyclerView.ViewHolder(viewbingding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailHolder {
        return ThumbnailHolder(ItemThumbnailBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ThumbnailHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 100
    }
}