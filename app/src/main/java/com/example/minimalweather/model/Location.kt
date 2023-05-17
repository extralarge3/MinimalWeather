package com.example.minimalweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    //val country: String
    )
{}