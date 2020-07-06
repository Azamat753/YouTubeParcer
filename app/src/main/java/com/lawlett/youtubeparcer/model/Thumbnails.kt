package com.lawlett.youtubeparcer.model

import android.text.style.AlignmentSpan
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default") @Expose
    private var _default: Default? = null,
    @SerializedName("medium")
@Expose
private val medium: Medium? = null,

    @SerializedName("high")
@Expose
private val high: High? = null,

    @SerializedName("standard")
@Expose
private val standard: AlignmentSpan.Standard? = null,

    @SerializedName("maxres")
@Expose
private val maxres: Maxres? = null
)
