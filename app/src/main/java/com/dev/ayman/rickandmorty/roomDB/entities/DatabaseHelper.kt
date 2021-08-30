package com.dev.ayman.rickandmorty.roomDB.entities



interface DatabaseHelper {
    suspend fun getAllCharacters(): List<Characters>
    suspend fun isFavorite(id:Int): Boolean



    suspend fun insertCharacter(character: Characters)
    suspend fun removeFavoriteCharacter(character: Characters)


}