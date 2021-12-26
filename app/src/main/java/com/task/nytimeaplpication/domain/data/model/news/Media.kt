package com.task.nytimeaplpication.domain.data.model.news


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    @SerializedName("approved_for_syndication")
    @Expose
    var approvedForSyndication: Int,
    @SerializedName("caption")
    @Expose
    var caption: String,
    @SerializedName("copyright")
    @Expose
    var copyright: String,
    @SerializedName("media-metadata")
    @Expose
    var mediaMetadata: List<MediaMetadata>,
    @SerializedName("subtype")
    @Expose
    var subtype: String,
    @SerializedName("type")
    @Expose
    var type: String
):Parcelable