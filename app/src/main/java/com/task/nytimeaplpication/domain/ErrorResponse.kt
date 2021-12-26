package com.task.nytimeaplpication.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by Amer Elsayed.
 *12/4/2021
 *dev.amer.elsayed@gmail.com
 */

data class ErrorResponse(
    @SerializedName("cod")
    val code: String = "",
    @SerializedName("message")
    val message: String = ""
)