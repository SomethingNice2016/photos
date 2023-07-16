package com.somenthingnice.photos.domain.photos

import com.somenthingnice.photos.di.IODispatcher
import com.somenthingnice.photos.repositiry.photos.PhotosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photosRepository: PhotosRepository,
    @IODispatcher
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke() = photosRepository.getPhotos()
        .flowOn(dispatcher)

}