package com.lawlett.youtubeparcer.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Default(
    @SerializedName("url")
    @Expose
    private var url: String? = null,
    @SerializedName("width")
    @Expose
    private var width:Int?=null,
    @SerializedName("height")
    @Expose
    private var height:Int?=null
)

