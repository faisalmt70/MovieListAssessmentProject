package com.example.assessmentproject.data.databass.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val image: String,
    val description: String,
    val releaseYear: String
)
