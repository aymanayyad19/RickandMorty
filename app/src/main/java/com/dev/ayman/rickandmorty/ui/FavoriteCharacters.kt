package com.dev.ayman.rickandmorty.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.ayman.rickandmorty.adapters.MainAdapter
import com.dev.ayman.rickandmorty.databinding.ActivityFavoriteCharactersBinding
import com.dev.ayman.rickandmorty.retrofit.ApiImp
import com.dev.ayman.rickandmorty.retrofit.RetrofitBuilder
import com.dev.ayman.rickandmorty.retrofit.Status
import com.dev.ayman.rickandmorty.roomDB.AppDatabase
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import com.dev.ayman.rickandmorty.roomDB.entities.DatabaseHelperImpl
import com.dev.ayman.rickandmorty.viewModel.DBViewModel
import com.dev.ayman.rickandmorty.viewModel.ViewModelFactory
import com.google.gson.Gson

class FavoriteCharacters : AppCompatActivity() {
    lateinit var binding: ActivityFavoriteCharactersBinding
    lateinit var viewModel: DBViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getCharacters()
        viewModel.getAllCharacters()

    }

    private fun getCharacters() {
        viewModel.getAllCharactersAsLiveData().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { list ->
                        Log.e("TAG", "getSearchResult: ${list.toString()}")
                        initRecycleView(it.data)
                        binding.progressBar.visibility = View.GONE
                    }
                }
                Status.EMPTY -> {
                    binding.progressBar.visibility = View.GONE

                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    // Handle Error
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun initRecycleView(list: List<Characters>?) {


        val mainAdapter = list?.let { MainAdapter(it) }
        binding.asteroidRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
            setHasFixedSize(true)
        }


        mainAdapter?.onItemClick = {
            val intent = Intent(this,CharacterDetailsActivity::class.java)
            intent.putExtra("Item", Gson().toJson(it))
            startActivity(intent)
        }
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