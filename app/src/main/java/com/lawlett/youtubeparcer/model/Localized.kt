package com.lawlett.youtubeparcer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Localized(
    @SerializedName("title") @Expose
    private var title: String? = null,
    @SerializedName("description")
    @Expose
    private val description: String? = null
)