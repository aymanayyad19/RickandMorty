package com.dev.ayman.rickandmorty.roomDB.entities

import com.dev.ayman.rickandmorty.roomDB.AppDatabase
import java.lang.Character


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getAllCharacters(): List<Characters> {
        return appDatabase.daoCharacters().getAllCharacters()
    }

    override suspend fun isFavorite(id:Int): Boolean {
        return appDatabase.daoCharacters().getIsFavorite(id) != null
    }

    override suspend fun insertCharacter(character: Characters) {
        return appDatabase.daoCharacters().insertCharacters(character)
    }

    override suspend fun removeFavoriteCharacter(character: Characters) {
        return appDatabase.daoCharacters().insertCharacters(character)
    }


}