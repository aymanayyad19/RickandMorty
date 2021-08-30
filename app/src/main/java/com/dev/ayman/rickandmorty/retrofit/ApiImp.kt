package com.dev.ayman.rickandmorty.retrofit
import android.content.Context
import com.dev.ayman.rickandmorty.models.MainResponse
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import okhttp3.ResponseBody

class ApiImp(private val apiService: ApiService) : ApiHelper {
//    companion object {
//        private var INSTANCE: ApiImp? = null
//
//        fun getInstance(): ApiImp {
//            if (INSTANCE == null) {
//                INSTANCE = ApiImp(RetrofitBuilder.apiService)
//            }
//            return INSTANCE!!
//        }
//
//
//    }

    override suspend fun getCharacter(): MainResponse<List<Characters>> {
        return apiService.getCharacter()
    }

    override suspend fun getLocations(): ResponseBody {
        return apiService.getLocations()
    }

    override suspend fun getEpisodes(): ResponseBody {
        return apiService.getEpisodes()
    }

}