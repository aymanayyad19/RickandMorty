package com.dev.ayman.rickandmorty.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.ayman.rickandmorty.adapters.MainAdapter
import com.dev.ayman.rickandmorty.databinding.ActivityCharactersBinding
import com.dev.ayman.rickandmorty.models.MainResponse
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.retrofit.ApiImp
import com.dev.ayman.rickandmorty.retrofit.RetrofitBuilder
import com.dev.ayman.rickandmorty.retrofit.Status
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import com.dev.ayman.rickandmorty.viewModel.MainViewModel
import com.dev.ayman.rickandmorty.viewModel.ViewModelFactory
import com.google.gson.Gson

class CharactersActivity : AppCompatActivity() {
    lateinit var binding: ActivityCharactersBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getCharacters()
        viewModel.fetchCharacters()

    }

    private fun initViewModel() {
        val apiHelper = ApiImp(RetrofitBuilder.apiService)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(apiHelper,null)
        ).get(MainViewModel::class.java)

    }


    private fun getCharacters() {
        viewModel.getCharactersAsLiveData().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { list ->
                        Log.e("TAG", "getSearchResult: ${list.toString()}")
                        initRecycleView(list)
                    }
                    binding.progressBar.visibility = View.GONE
                }
                Status.EMPTY -> {

                    binding.progressBar.visibility = View.GONE


                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    // Handle Error
                }
            }
        })
    }

    private fun initRecycleView(list: MainResponse<List<Characters>>) {


        val mainAdapter = list.getResults()?.let { MainAdapter(it) }
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

}