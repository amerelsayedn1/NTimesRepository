package com.task.nytimeaplpication.domain.data.model.error


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Fault(
    @SerializedName("detail")
    @Expose
    var detail: Detail,
    @SerializedName("faultstring")
    @Expose
    var faultstring: String
)