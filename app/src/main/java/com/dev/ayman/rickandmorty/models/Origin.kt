package com.dev.ayman.rickandmorty.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Origin {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}