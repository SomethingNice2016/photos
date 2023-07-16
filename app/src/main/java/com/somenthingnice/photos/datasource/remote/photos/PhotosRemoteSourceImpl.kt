package com.somenthingnice.photos.datasource.remote.photos

import javax.inject.Inject

class PhotosRemoteSourceImpl @Inject constructor(
    private val photosApi: PhotosApi
): PhotosRemoteSource {

    override fun getPhotos() = PhotosPagingSource(photosApi)
}