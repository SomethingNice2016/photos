package com.somenthingnice.photos.datasource.remote.auth

import com.somenthingnice.photos.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        private const val CLIENT_ID_KEY = "client_id"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(CLIENT_ID_KEY, BuildConfig.CLIENT_ID)
            .build()
        val newRequest = request.newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }

}