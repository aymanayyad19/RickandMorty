package com.dev.ayman.rickandmorty.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.ayman.rickandmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCharacters.setOnClickListener {
            val intent = Intent(this,CharactersActivity::class.java)
            startActivity(intent)
        }
        binding.btnEpisodes.setOnClickListener {


        }
        binding.btnLocations.setOnClickListener {

            val intent = Intent(this,FavoriteCharacters::class.java)
            startActivity(intent)
        }
    }
}