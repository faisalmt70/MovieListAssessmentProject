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
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<MovieModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<MovieModel>>> = _uiState

    private val _showFavoritesOnly = MutableStateFlow(false)

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val movies = repository.getMovies()
                _uiState.value = UiState.Success(movies)
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
                val updatedList = currentState.data.map {
                    if (it.id == movie.id) it.copy(isFavorite = !it.isFavorite) else it
                }
                _uiState.value = UiState.Success(updatedList)
            }
        }
    }

    fun toggleFilter() {
        _showFavoritesOnly.value = !_showFavoritesOnly.value
    }
}
