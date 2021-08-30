package com.dev.ayman.rickandmorty.roomDB.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dev.ayman.rickandmorty.roomDB.entities.Characters

@Dao
interface DaoCharacters {
    @Query("SELECT * FROM characters_favorite_tb ")
    suspend fun getAllCharacters(): List<Characters>

    @Query("SELECT * FROM characters_favorite_tb where id = :id  ")
    suspend fun getIsFavorite(id: Int): Characters?


    @Insert
    suspend fun insertCharacters(characters: Characters)

    @Delete
    suspend fun removeFavoriteCharacter(characters: Characters)



}