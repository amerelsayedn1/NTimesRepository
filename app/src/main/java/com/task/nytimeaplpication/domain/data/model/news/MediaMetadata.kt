package com.task.nytimeaplpication.domain.data.model.news


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaMetadata(
    @SerializedName("format")
    @Expose
    var format: String,
    @SerializedName("height")
    @Expose
    var height: Int,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("width")
    @Expose
    var width: Int
):Parcelable