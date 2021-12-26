package com.task.nytimeaplpication.domain.data.model.news

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    @SerializedName("abstract")
    @Expose
    var abstract: String,
    @SerializedName("asset_id")
    @Expose
    var assetId: Long,
    @SerializedName("byline")
    @Expose
    var byline: String,
    @SerializedName("eta_id")
    @Expose
    var etaId: Int,
    @SerializedName("id")
    @Expose
    var id: Long,
    @SerializedName("media")
    @Expose
    var media: List<Media>,
    @SerializedName("nytdsection")
    @Expose
    var nytdsection: String,
    @SerializedName("published_date")
    @Expose
    var publishedDate: String,
    @SerializedName("section")
    @Expose
    var section: String,
    @SerializedName("source")
    @Expose
    var source: String,
    @SerializedName("subsection")
    @Expose
    var subsection: String,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("updated")
    @Expose
    var updated: String,
    @SerializedName("uri")
    @Expose
    var uri: String,
    @SerializedName("url")
    @Expose
    var url: String
) : Parcelable {
    val image: String
        get() = if (media.isNotEmpty() && media[0].mediaMetadata.isNotEmpty()) media[0].mediaMetadata[1].url else ""
}