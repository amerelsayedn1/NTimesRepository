package com.task.nytimeaplpication.domain.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Amer Elsayed.
 *12/25/2021
 *dev.amer.elsayed@gmail.com
 */
data class BaseModel<T>(
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val data: T
)