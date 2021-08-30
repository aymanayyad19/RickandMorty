package com.dev.ayman.rickandmorty.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.ayman.rickandmorty.models.MainResponse
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.retrofit.Resource
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import com.dev.ayman.rickandmorty.roomDB.entities.DatabaseHelper
import kotlinx.coroutines.launch

class DBViewModel(private val dbHelper: DatabaseHelper): ViewModel() {

    private val favoriteCharacters = MutableLiveData<Resource<Boolean>>()
    private val allCharacters = MutableLiveData<Resource<List<Characters>>>()

    fun isFavorite(id:Int){
        viewModelScope.launch {
            favoriteCharacters.postValue(Resource.loading(null))

            try {
                val dataApi = dbHelper.isFavorite(id)
                favoriteCharacters.postValue(Resource.success(dataApi))
            } catch (ex: Exception) {
                favoriteCharacters.postValue(Resource.error(ex.message.toString(),null))
            }
        }
    }
 fun getAllCharacters(){
        viewModelScope.launch {
            allCharacters.postValue(Resource.loading(null))

            try {
                val dataApi = dbHelper.getAllCharacters()
                allCharacters.postValue(Resource.success(dataApi))
            } catch (ex: Exception) {
                allCharacters.postValue(Resource.error(ex.message.toString(),null))
            }
        }
    }


    fun addCharacter(item:Characters){
        viewModelScope.launch {
            favoriteCharacters.postValue(Resource.loading(null))

            try {
                val dataApi = dbHelper.insertCharacter(item)
                favoriteCharacters.postValue(Resource.success(true))
            } catch (ex: Exception) {
                favoriteCharacters.postValue(Resource.error(ex.message.toString(),null))
            }
        }
    }

 fun deleteCharacter(item:Characters){
        viewModelScope.launch {
            favoriteCharacters.postValue(Resource.loading(null))

            try {
                val dataApi = dbHelper.removeFavoriteCharacter(item)
                favoriteCharacters.postValue(Resource.success(false))
            } catch (ex: Exception) {
                favoriteCharacters.postValue(Resource.error(ex.message.toString(),null))
            }
        }
    }
    fun getIsFavoriteAsLiveData(): LiveData<Resource<Boolean>> {
        return favoriteCharacters
    }
 fun getAllCharactersAsLiveData(): LiveData<Resource<List<Characters>>> {
        return allCharacters
    }


}