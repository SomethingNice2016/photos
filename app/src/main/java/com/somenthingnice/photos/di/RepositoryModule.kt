package com.somenthingnice.photos.di

import com.somenthingnice.photos.repositiry.photos.PhotosRepository
import com.somenthingnice.photos.repositiry.photos.PhotosRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindPhotosRepository(impl: PhotosRepositoryImpl): PhotosRepository


}