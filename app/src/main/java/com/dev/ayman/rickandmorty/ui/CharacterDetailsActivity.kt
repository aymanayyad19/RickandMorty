package com.dev.ayman.rickandmorty.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.dev.ayman.rickandmorty.databinding.ActivityCharacterDetailsBinding
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.retrofit.ApiImp
import com.dev.ayman.rickandmorty.retrofit.RetrofitBuilder
import com.dev.ayman.rickandmorty.retrofit.Status
import com.dev.ayman.rickandmorty.roomDB.AppDatabase
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import com.dev.ayman.rickandmorty.roomDB.entities.DatabaseHelperImpl
import com.dev.ayman.rickandmorty.viewModel.DBViewModel
import com.dev.ayman.rickandmorty.viewModel.MainViewModel
import com.dev.ayman.rickandmorty.viewModel.ViewModelFactory
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class CharacterDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCharacterDetailsBinding
    lateinit var viewModel: DBViewModel
    lateinit var characterResult: Characters

    var isFavorite = false;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initData()


        binding.btnFavorite.setOnClickListener {
            if (isFavorite) {
                viewModel.deleteCharacter(characterResult)
            } else {
                viewModel.addCharacter(characterResult)
            }
        }
    }

    private fun initData() {
        val itemJson = intent.getStringExtra("Item")
        characterResult = Gson().fromJson(itemJson, Characters::class.java);
        setUserData()
        Picasso.get().load(characterResult.image).into(binding.imgUser)
        getCharacters()
        viewModel.isFavorite(characterResult.id)


    }

    private fun setUserData() {
        binding.txtDetails.text = "Name : ${characterResult.name} ,\n" +
                "Gender : ${characterResult.gender} ,\n" +
                "Type : ${characterResult.type} ,\n" +
                "Status : ${characterResult.status} ,\n" +
                "Species : ${characterResult.species} ,\n" +
                "Is Favorite : ${isFavorite} ,\n"
        binding.btnFavorite.text =
            if (isFavorite) "Remove Form Favorite List" else "Add To Favorite List"
    }

    private fun getCharacters() {
        viewModel.getIsFavoriteAsLiveData().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { list ->
                        Log.e("TAG", "getSearchResult: ${list.toString()}")
                        isFavorite = it.data
                        setUserData()
                    }
                }
                Status.EMPTY -> {


                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    // Handle Error
                }
            }
        })
    }

    private fun initViewModel() {
        val dbHelper = DatabaseHelperImpl(AppDatabase.getInstance(this))
        val apiHelper = ApiImp(RetrofitBuilder.apiService)


        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(apiHelper, dbHelper)
        ).get(DBViewModel::class.java)

    }

}