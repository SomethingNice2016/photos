package com.somenthingnice.photos.features.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.somenthingnice.photos.entity.photos.Photo

class PhotosPagingAdapter(
    private val callback: PhotoViewHolder.Callback
) : PagingDataAdapter<Photo, PhotoViewHolder>(PhotosDiffCallback()) {


    override fun onBindViewHolder(
        holder: PhotoViewHolder,
        position: Int
    ) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = PhotoViewHolder.create(parent, callback)

    class PhotosDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(
            oldItem: Photo,
            newItem: Photo
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Photo,
            newItem: Photo
        ) = newItem == oldItem

    }
}