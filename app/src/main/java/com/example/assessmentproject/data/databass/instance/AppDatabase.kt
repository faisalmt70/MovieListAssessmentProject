package com.example.assessmentproject.data.databass.instance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessmentproject.data.databass.dao.MovieDao
import com.example.assessmentproject.data.databass.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
