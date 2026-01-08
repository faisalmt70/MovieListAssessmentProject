package com.example.assessmentproject.data.repository

import com.example.assessmentproject.data.databass.dao.MovieDao
import com.example.assessmentproject.data.databass.entity.MovieEntity
import com.example.assessmentproject.data.model.MovieModel
import com.example.assessmentproject.data.remote.interfaces.GhibliApiService
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: GhibliApiService, private val dao: MovieDao) {
    suspend fun getMovies(): List<MovieModel> {
        val favoriteIds = dao.getFavorites().first().map { it.id }.toSet()

        return api.getMovies().map {
            MovieModel(
                id = it.id,
                title = it.title,
                image = it.image,
                description = it.description,
                releaseYear = it.release_date,
                isFavorite = favoriteIds.contains(it.id)
            )
        }
    }

    suspend fun toggleFavorite(movie: MovieModel) {
        if (dao.isFavorite(movie.id)) {
            dao.delete(movie.toEntity())
        } else {
            dao.insert(movie.toEntity())
        }
    }
}
private fun MovieModel.toEntity() = MovieEntity(id, title, image, description, releaseYear)
