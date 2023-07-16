package com.somenthingnice.photos.repositiry.photos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.somenthingnice.photos.datasource.remote.photos.PhotosRemoteSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepositoryImpl @Inject constructor(
    private val remote: PhotosRemoteSource
) : PhotosRepository {

    companion object {
        private const val PAGE_SIZE = 5
    }

    override fun getPhotos() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = { remote.getPhotos() }
    ).flow

}