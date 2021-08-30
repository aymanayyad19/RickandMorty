package com.dev.ayman.rickandmorty.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.ayman.rickandmorty.roomDB.dao.DaoCharacters
import com.dev.ayman.rickandmorty.roomDB.entities.Characters

@Database(entities = [Characters::class], version =1)

abstract class AppDatabase : RoomDatabase(){
    abstract fun daoCharacters(): DaoCharacters
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = initDatabase(context)

            }
            return INSTANCE!!
        }

        private fun initDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "myDb").build()
        }
    }
}