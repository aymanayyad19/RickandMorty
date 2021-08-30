package com.dev.ayman.rickandmorty.retrofit
import com.dev.ayman.rickandmorty.models.MainResponse
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("character")
    suspend fun getCharacter(
    ): MainResponse<List<Characters>>

    @GET("locations")
    suspend fun getLocations(
    ): ResponseBody


    @GET("episodes")
    suspend fun getEpisodes(
    ): ResponseBody




}
