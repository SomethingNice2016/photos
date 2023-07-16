package com.somenthingnice.photos.datasource.remote.photos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.somenthingnice.photos.datasource.remote.entity.toDomain
import com.somenthingnice.photos.entity.photos.Photo

class PhotosPagingSource(
    private val photosApi: PhotosApi
) : PagingSource<Int, Photo>() {

    companion object {
        private const val INITIAL_KEY = 1

        private const val PER_PAGE = 10
    }

    override fun getRefreshKey(
        state: PagingState<Int, Photo>
    ) = INITIAL_KEY

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Photo> {
        val key = params.key ?: INITIAL_KEY
        return runCatching {
            val data = photosApi.getPhotos(
                page = key,
                perPage = PER_PAGE
            ).map {
                it.toDomain()
            }

            if (data.isEmpty()) throw NoSuchElementException()

            LoadResult.Page(
                data = data,
                prevKey = if (key == INITIAL_KEY) null else key.inc(),
                nextKey = key.inc()
            )

        }.getOrElse {
            it.printStackTrace()
            LoadResult.Error(it)
        }
    }
}