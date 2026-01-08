package com.example.assessmentproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmentproject.data.model.MovieModel
import com.example.assessmentproject.data.repository.MovieRepository
import com.example.assessmentproject.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MovieModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<MovieModel>> = _uiState

    fun loadMovie(movieId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val movie = repository.getMovies()
                    .first { it.id == movieId }

                _uiState.value = UiState.Success(movie)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message)
            }
        }
    }

    fun toggleFavorite(movie: MovieModel) {
        viewModelScope.launch {
            repository.toggleFavorite(movie)

            val currentState = _uiState.value
            if (currentState is UiState.Success) {
                val updatedMovie = currentState.data.copy(
                    isFavorite = !currentState.data.isFavorite
                )
                _uiState.value = UiState.Success(updatedMovie)
            }
        }
    }

}
