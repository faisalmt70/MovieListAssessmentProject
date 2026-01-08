package com.example.assessmentproject.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assessmentproject.data.model.MovieModel

@Composable
fun MovieDetailsView(movie: MovieModel, onFavoriteClick: () -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {

        AsyncImage(model = movie.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(260.dp), contentScale = ContentScale.Crop)

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = movie.title, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.weight(1f))

            IconButton(onClick = onFavoriteClick) {
                Icon(imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null, tint = Color.Red)
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(text = "Release Year: ${movie.releaseYear}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

        Spacer(Modifier.height(12.dp))

        Text(text = movie.description, style = MaterialTheme.typography.bodyLarge, lineHeight = 22.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun previewDetailsView(){
    MovieDetailsView(movie = MovieModel("1", "Spiderman", "", "This is movie description", "2024", false), onFavoriteClick = {})
}
