package com.dev.ayman.rickandmorty.roomDB.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity(tableName = "characters_favorite_tb")
@Parcelize
data class Characters(
    @PrimaryKey()
    var id: Int,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: String? = null,
    var image: String? = null,
    var url: String? = null


): Parcelable
