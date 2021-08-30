package com.dev.ayman.rickandmorty.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

public class MainResponse<T> {
    @SerializedName("info")
    @Expose
    private var info: Info? = null

    @SerializedName("results")
    @Expose
    private var results: T? = null

    fun getInfo(): Info? {
        return info
    }

    fun setInfo(info: Info?) {
        this.info = info
    }

    fun getResults(): T? {
        return results
    }

    fun setResults(results: T?) {
        this.results = results
    }
}

