package com.lawlett.youtubeparcer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("kind")
    @Expose
    private var kind: String? = null,
    @SerializedName("etag")
    @Expose
    private var etag: String? = null,
    @SerializedName("id")
    @Expose
    private val id: String? = null,
    @SerializedName("snippet")
    @Expose
    private val snippet: Snippet? = null,
    @SerializedName("contentDetails")
    @Expose
    private val contentDetails: ContentDetails? = null

)
