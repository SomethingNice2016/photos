package com.somenthingnice.photos.repositiry.photos

import androidx.paging.PagingData
import com.somenthingnice.photos.entity.photos.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getPhotos(): Flow<PagingData<Photo>>

}