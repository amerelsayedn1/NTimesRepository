package com.task.nytimeaplpication.utils.extension

import android.content.Context
import com.task.nytimeaplpication.AndroidApplication

/**
 * Created by Amer Elsayed.
 *12/3/2021
 *dev.amer.elsayed@gmail.com
 */



fun getContext(): Context {
    return AndroidApplication.instance.applicationContext
}