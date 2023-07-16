package com.somenthingnice.photos.datasource.remote.entity

import com.somenthingnice.photos.entity.photos.Photo
import com.somenthingnice.photos.entity.photos.Urls
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "urls")
    val urls: UrlsResponse,
)

@JsonClass(generateAdapter = true)
data class UrlsResponse(
    @Json(name = "full")
    val full: String,
    @Json(name = "raw")
    val raw: String,
    @Json(name = "regular")
    val regular: String,
    @Json(name = "small")
    val small: String,
    @Json(name = "thumb")
    val thumb: String
)

fun PhotoResponse.toDomain() = Photo(
    id = this.id,
    urls = urls.toDomain(),
)

fun UrlsResponse.toDomain() = Urls(
    full = full,
    raw = raw,
    regular = regular,
    thumb = thumb,
    small = small
)
