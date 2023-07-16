package com.somenthingnice.photos.features

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.somenthingnice.photos.R
import com.somenthingnice.photos.core.ui.view.gone
import com.somenthingnice.photos.core.ui.view.visible
import com.somenthingnice.photos.core.ui.viewBinding.viewBinding
import com.somenthingnice.photos.databinding.DialogImageDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailDialog : DialogFragment(R.layout.dialog_image_detail), RequestListener<Drawable?> {

    private val args: ImageDetailDialogArgs by navArgs()

    private val binding: DialogImageDetailBinding by viewBinding()

    override fun getTheme() = R.style.TransparentDialogTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(args.url)
            .addListener(this)
            .submit()
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
    ) = with(binding) {
        root.post {
            progressBar.gone()
            image.visible()
            Glide.with(requireContext())
                .load(resource)
                .into(image)
        }
    }

}