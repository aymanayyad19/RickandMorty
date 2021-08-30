package com.dev.ayman.rickandmorty.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Info {
    @SerializedName("count")
    @Expose
    var count = 0

    @SerializedName("pages")
    @Expose
    var pages = 0

    @SerializedName("next")
    @Expose
    var next: String? = null

    @SerializedName("prev")
    @Expose
    var prev: Any? = null
}