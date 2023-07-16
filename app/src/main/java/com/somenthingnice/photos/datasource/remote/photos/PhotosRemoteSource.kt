package com.somenthingnice.photos.datasource.remote.photos

import androidx.paging.PagingSource
import com.somenthingnice.photos.entity.photos.Photo

interface PhotosRemoteSource {

    fun getPhotos(): PagingSource<Int, Photo>

}