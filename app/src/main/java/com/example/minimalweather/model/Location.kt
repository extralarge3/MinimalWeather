package com.example.minimalweather.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "location",
        indices = [Index(value = ["name"], unique = true)])
data class Location(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    //val country: String
    )
{}