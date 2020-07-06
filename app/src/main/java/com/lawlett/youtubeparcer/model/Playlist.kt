package com.lawlett.youtubeparcer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("kind")
    @Expose var kind: String? = null,
    @SerializedName("etag")
    @Expose
    private val etag: String? = null,
    @SerializedName("nextPageToken")
    @Expose
    private val nextPageToken: String? = null,

    @SerializedName("pageInfo")
    @Expose
    private val pageInfo: PageInfo? = null,

    @SerializedName("items")
    @Expose
    private val items: List<Item>? = null

)