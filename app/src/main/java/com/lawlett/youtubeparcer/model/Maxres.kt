package com.lawlett.youtubeparcer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Maxres(
    @SerializedName("url") @Expose
    private var url: String? = null,
    @SerializedName("width")
    @Expose
    private val width: Int? = null,

    @SerializedName("height")
    @Expose
    private val height: Int? = null
)