package com.example.assessmentproject.data.database.instance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessmentproject.data.database.dao.MovieDao
import com.example.assessmentproject.data.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
