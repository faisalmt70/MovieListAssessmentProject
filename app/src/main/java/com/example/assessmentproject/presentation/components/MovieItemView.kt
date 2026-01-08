package com.example.assessmentproject.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.assessmentproject.data.model.MovieModel

@Composable
fun MovieItemView(
    movie: MovieModel,
    onClick: () -> Unit,
    onFavorite: () -> Unit
) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth().clickable(onClick = onClick)) {
        Row {
            AsyncImage(model = movie.image, contentDescription = null, modifier = Modifier.size(100.dp))
            Column(modifier = Modifier.padding(8.dp)) {
                Text(movie.title, fontWeight = FontWeight.Bold)
                Text(movie.releaseYear)
                IconButton(onClick = onFavorite) {
                    Icon(imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun itemView(){
    MovieItemView(movie = MovieModel("1", "Spiderman", "", "This is movie description", "2024", false), onClick = {}, onFavorite = {})
}
