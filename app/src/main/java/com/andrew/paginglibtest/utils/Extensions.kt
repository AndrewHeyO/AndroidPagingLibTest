package com.andrew.paginglibtest.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Andrew on 03.06.2018.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
            .load(url)
            .apply(RequestOptions().circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
            .into(this)
}