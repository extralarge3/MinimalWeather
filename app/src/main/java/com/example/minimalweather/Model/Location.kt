package com.example.minimalweather.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(
    @PrimaryKey val id: Int,
    val name: String,
    val country: String
    )
{}