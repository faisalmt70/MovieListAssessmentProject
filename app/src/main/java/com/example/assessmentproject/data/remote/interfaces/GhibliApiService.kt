package com.example.assessmentproject.data.remote.interfaces

import com.example.assessmentproject.data.remote.data.MovieDtoModel
import retrofit2.http.GET

interface GhibliApiService {
    @GET("films")
    suspend fun getMovies(): List<MovieDtoModel>
}