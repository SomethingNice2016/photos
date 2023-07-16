package com.somenthingnice.photos.di

import com.somenthingnice.photos.datasource.remote.photos.PhotosRemoteSource
import com.somenthingnice.photos.datasource.remote.photos.PhotosRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RemoteModule {

    @Binds
    fun bindsPhotosRemoteSource(impl: PhotosRemoteSourceImpl): PhotosRemoteSource

}