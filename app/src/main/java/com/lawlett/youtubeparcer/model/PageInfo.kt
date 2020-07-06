package com.lawlett.youtubeparcer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("totalResults") @Expose
    private var totalResults: Int? = null,
    @SerializedName("resultsPerPage")
    @Expose
    private val resultsPerPage: Int? = null
)