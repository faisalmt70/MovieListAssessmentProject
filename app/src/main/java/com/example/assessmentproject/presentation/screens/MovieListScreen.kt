package com.example.assessmentproject.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.assessmentproject.R
import com.example.assessmentproject.presentation.components.MovieItemView
import com.example.assessmentproject.presentation.viewmodel.MovieListViewModel
import com.example.assessmentproject.util.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    var showFavorites by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray
                ))
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = { showFavorites = false }, colors = ButtonDefaults.buttonColors(
                            containerColor = if (!showFavorites) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)) {
                        Text(stringResource(R.string.all)) }

                    Button(
                        onClick = { showFavorites = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (showFavorites) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    ) { Text(stringResource(R.string.favorites)) }
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (state) {
                    is UiState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                    }
                    is UiState.Error -> {
                        Column {
                            Text(stringResource(R.string.error_loading_movies))
                            Button(onClick = { viewModel.loadMovies() }) {
                                Text(stringResource(R.string.retry))
                            }
                        }
                    }
                    is UiState.Success -> {
                        val moviesToShow = if (showFavorites) {
                            state.data.filter { it.isFavorite }
                        } else state.data

                        LazyColumn {
                            items(moviesToShow) { movie ->
                                MovieItemView(
                                    movie = movie,
                                    onClick = { navController.navigate("details/${movie.id}") },
                                    onFavorite = { viewModel.toggleFavorite(movie) }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun viewScreen() {
    MovieListScreen(navController = NavController(LocalContext.current))
}
