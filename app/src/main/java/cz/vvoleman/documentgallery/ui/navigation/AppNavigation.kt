package cz.vvoleman.documentgallery.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import cz.vvoleman.documentgallery.ListScreen
import cz.vvoleman.documentgallery.presentation.viewmodel.ListViewModel

fun mainNavigation(navBuilder: NavGraphBuilder, navController: NavController) {
    navBuilder.apply {
        composable(Destination.Home.route) {
            val viewModel = hiltViewModel<ListViewModel>()
            val state by viewModel.viewState.collectAsState()

            ListScreen(
                state = state!!,
                onEvent = { event ->
                    viewModel.onEvent(event)
                }
            )
        }
    }
}

sealed class Destination(val route: String) {
    data object Home : Destination("home", )
    data object Folder : Destination("folder/{id}") {
        fun createRoute(id: String) = "folder/$id"
    }
}