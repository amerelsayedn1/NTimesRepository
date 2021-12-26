package com.task.nytimeaplpication.utils.extension

/**
 * Created by Amer Elsayed.
 *12/3/2021
 *dev.amer.elsayed@gmail.com
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import coil.load
import coil.transform.CircleCropTransformation
import com.task.nytimeaplpication.R


fun View.isVisible() = this.visibility == View.VISIBLE

/**
 * Set view visibility visible
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Set view visibility invisible
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Set view visibility gone
 */
fun View.gone() {
    visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadFromUrl(url: String) = run {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.place_holder)
        transformations(CircleCropTransformation())
    }
}

