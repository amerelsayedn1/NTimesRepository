package com.task.nytimeaplpication.domain.data.model.error


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ErrorResponse(
    @SerializedName("fault")
    @Expose
    var fault: Fault
)