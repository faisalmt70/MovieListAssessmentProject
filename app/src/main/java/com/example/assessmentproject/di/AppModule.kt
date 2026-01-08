package com.example.assessmentproject.di

import android.content.Context
import androidx.room.Room
import com.example.assessmentproject.data.database.instance.AppDatabase
import com.example.assessmentproject.data.remote.interfaces.GhibliApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApi(): GhibliApiService =
        Retrofit.Builder()
            .baseUrl("https://ghibliapi.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GhibliApiService::class.java)

    @Provides
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "ghibli.db").build()

    @Provides
    fun provideDao(db: AppDatabase) = db.movieDao()
}
