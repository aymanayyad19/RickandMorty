package com.dev.ayman.rickandmorty.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.ayman.rickandmorty.models.MainResponse
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.retrofit.ApiHelper
import com.dev.ayman.rickandmorty.retrofit.Resource
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(private val apiHelper: ApiHelper): ViewModel() {
    val TAG = "MainViewModel"
    private val characters = MutableLiveData<Resource<MainResponse<List<Characters>>>>()

    fun fetchCharacters(){
        viewModelScope.launch {
            characters.postValue(Resource.loading(null))

            try {
                val dataApi = apiHelper.getCharacter()
                characters.postValue(Resource.success(dataApi))
            } catch (ex: Exception) {
                Log.e(TAG, "fetchAsteroid: ${ex.message} ")
                characters.postValue(Resource.error(ex.message.toString(),null))
            }
        }
    }


    fun getCharactersAsLiveData(): LiveData<Resource<MainResponse<List<Characters>>>> {
        return characters
    }
}