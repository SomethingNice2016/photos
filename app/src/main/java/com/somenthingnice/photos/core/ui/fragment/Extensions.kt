package com.somenthingnice.photos.core.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

inline fun <reified E, reified F : Flow<E>> Fragment.collect(
    flow: F,
    crossinline collector: suspend (E) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        flow.collectLatest {
            collector.invoke(it)
        }
    }
}


fun Fragment.showShortToast(@StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLongToast(message: String?) {
    message?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
}


fun Fragment.showLongToast(@StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.openWebSite(url: String) {
    val formatUrl = if (!url.startsWith("http://") && !url.startsWith("https://"))
        "http://$url"
    else
        url
    startActivity(
        Intent(
            Intent.ACTION_VIEW, Uri.parse(formatUrl)
        )
    )
}

fun Fragment.shareLink(url: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(
        Intent.EXTRA_TEXT, url
    )
    startActivity(Intent.createChooser(intent, "Share by"))
}

inline fun <reified T : Parcelable> Fragment.navArg(key: String) = lazy {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireArguments().getParcelable(key, T::class.java)
    } else {
        requireArguments().getParcelable(key)
    }
}

inline fun <reified T : Parcelable> Fragment.requireNavArg(key: String) = lazy {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireArguments().getParcelable(key, T::class.java)!!
    } else {
        requireArguments().getParcelable(key)!!
    }
}