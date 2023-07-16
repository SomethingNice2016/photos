package com.somenthingnice.photos.core.ui.paging

import androidx.paging.PagingDataAdapter

fun PagingDataAdapter<*, *>.isEmpty() = itemCount == 0