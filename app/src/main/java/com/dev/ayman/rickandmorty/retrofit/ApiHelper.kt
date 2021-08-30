package com.dev.ayman.rickandmorty.retrofit

import com.dev.ayman.rickandmorty.models.MainResponse
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import okhttp3.ResponseBody

interface ApiHelper {
    suspend fun getCharacter(
    ): MainResponse<List<Characters>>

    suspend fun getLocations(
    ): ResponseBody

    suspend fun getEpisodes(
    ): ResponseBody


}