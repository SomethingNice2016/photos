package com.somenthingnice.photos.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.somenthingnice.photos.domain.photos.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    val photos = getPhotosUseCase()
        .cachedIn(viewModelScope)

}