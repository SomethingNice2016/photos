package com.somenthingnice.photos.features.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.somenthingnice.photos.R
import com.somenthingnice.photos.core.ui.fragment.collect
import com.somenthingnice.photos.core.ui.fragment.shareLink
import com.somenthingnice.photos.core.ui.paging.isEmpty
import com.somenthingnice.photos.core.ui.viewBinding.viewBinding
import com.somenthingnice.photos.databinding.FragmentHomeBinding
import com.somenthingnice.photos.entity.photos.Photo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), PhotoViewHolder.Callback {


    private val binding: FragmentHomeBinding by viewBinding()

    private val viewModel: HomeViewModel by viewModels()

    private val navigator by lazy {
        findNavController()
    }

    private val photosAdapter by lazy {
        PhotosPagingAdapter(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
        setupObservers()
    }

    private fun setupUI() = with(binding) {
        photosList.adapter = photosAdapter
        photosList.layoutManager = GridLayoutManager(context, 2)
//            .apply {
//            spanSizeLookup = object : SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int {
//                    return 2
//                }
//            }
//        }
    }

    private fun setupObservers() {
        collect(viewModel.photos) { pagingData ->
            photosAdapter.submitData(pagingData)
        }
        collect(photosAdapter.loadStateFlow) { loadState ->
            binding.progressBar.isVisible =
                loadState.refresh is LoadState.Loading && photosAdapter.isEmpty()
            binding.photosList.isVisible =
                loadState.refresh !is LoadState.Loading && !photosAdapter.isEmpty()
        }
    }

    private fun setupListeners() = with(binding) {
        swipeRefresh.setOnRefreshListener {
            photosAdapter.refresh()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onPhotoClick(photo: Photo) {
        navigator.navigate(HomeFragmentDirections.toDetail(photo.urls.full))
    }

    override fun onShareClick(photo: Photo) {
        shareLink(photo.urls.full)
    }


}