package com.dev.ayman.rickandmorty.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.ayman.rickandmorty.retrofit.ApiHelper
import com.dev.ayman.rickandmorty.roomDB.entities.DatabaseHelperImpl

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelperImpl?,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper) as T
        }else if (modelClass.isAssignableFrom(DBViewModel::class.java)) {
            return DBViewModel(dbHelper!!) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }


}