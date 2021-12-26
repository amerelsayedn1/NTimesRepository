package com.task.nytimeaplpication.utils.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Amer Elsayed.
 *12/3/2021
 *dev.amer.elsayed@gmail.com
 */





fun Long.getDateTime(): String {
    return try {
        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale("en"))
        val netDate = Date(this * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}