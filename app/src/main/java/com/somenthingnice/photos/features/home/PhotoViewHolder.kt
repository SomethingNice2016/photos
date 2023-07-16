package com.somenthingnice.photos.features.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.somenthingnice.photos.core.ui.view.gone
import com.somenthingnice.photos.core.ui.view.invisible
import com.somenthingnice.photos.core.ui.view.visible
import com.somenthingnice.photos.databinding.ItemPhotoBinding
import com.somenthingnice.photos.entity.photos.Photo


class PhotoViewHolder private constructor(
    private val binding: ItemPhotoBinding,
    private val callback: Callback
) : ViewHolder(binding.root), RequestListener<Drawable?> {


    companion object {
        fun create(parent: ViewGroup, callback: Callback) = PhotoViewHolder(
            binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            callback = callback
        )
    }

    fun bind(item: Photo) = with(binding) {
        progressBar.visible()
        image.invisible()
        Glide.with(itemView)
            .load(item.urls.regular)
            .addListener(this@PhotoViewHolder)
            .submit()

        btnShare.setOnClickListener {
            callback.onShareClick(item)
        }
        root.setOnClickListener {
            callback.onPhotoClick(item)
        }

    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable?>?,
        isFirstResource: Boolean
    ) = false

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable?>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean = with(binding) {
        root.post {
            image.setImageDrawable(resource)
            progressBar.gone()
            image.visible()
        }
    }

    interface Callback {
        fun onPhotoClick(photo: Photo)
        fun onShareClick(photo: Photo)
    }
}