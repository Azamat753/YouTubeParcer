package com.lawlett.youtubeparcer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentDetails(
    @SerializedName("itemCount")
    @Expose
    private var itemCount:Int

)