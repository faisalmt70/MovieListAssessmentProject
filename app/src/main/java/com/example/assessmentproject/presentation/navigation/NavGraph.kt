package com.example.assessmentproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.assessmentproject.presentation.screens.MovieDetailsScreen
import com.example.assessmentproject.presentation.screens.MovieListScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "movies"
    ) {
        composable("movies") {
            MovieListScreen(navController)
        }

        composable(
            route = "details/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.StringType }
            )
        ) {
            MovieDetailsScreen(
                movieId = it.arguments?.getString("movieId")!!,
                navController = navController
            )
        }
    }
}
