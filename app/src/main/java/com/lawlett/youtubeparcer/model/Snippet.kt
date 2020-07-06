package com.lawlett.youtubeparcer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("publishedAt") @Expose
    private var publishedAt: String? = null,
    @SerializedName("channelId")
@Expose
private val channelId: String? = null,

@SerializedName("title")
@Expose
private val title: String? = null,

@SerializedName("description")
@Expose
private val description: String? = null,

@SerializedName("thumbnails")
@Expose
private val thumbnails: Thumbnails? = null,

@SerializedName("channelTitle")
@Expose
private val channelTitle: String? = null,

@SerializedName("localized")
@Expose
private val localized: Localized? = null
)