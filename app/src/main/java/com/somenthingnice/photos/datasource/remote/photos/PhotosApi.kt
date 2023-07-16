package com.somenthingnice.photos.datasource.remote.photos

import com.somenthingnice.photos.datasource.remote.entity.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<PhotoResponse>

}