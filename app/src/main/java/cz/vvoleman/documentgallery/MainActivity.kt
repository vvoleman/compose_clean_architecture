package cz.vvoleman.documentgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import cz.vvoleman.documentgallery.base.presentation.model.UiState
import cz.vvoleman.documentgallery.presentation.model.list.ListEvent
import cz.vvoleman.documentgallery.presentation.model.list.ListViewState
import cz.vvoleman.documentgallery.presentation.viewmodel.ListViewModel
import cz.vvoleman.documentgallery.ui.navigation.Destination
import cz.vvoleman.documentgallery.ui.theme.DocumentGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DocumentGalleryTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destination.Home.route,
                ) {
                    composable("home") {
                        val viewModel = hiltViewModel<ListViewModel>()
                        val state by viewModel.viewState.collectAsState()
                        ListScreen(
                            state = state!!,
                            onEvent = { event ->
                                viewModel.onEvent(event)
                            }
                        )
                        navigation()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DocumentGalleryTheme {
        Greeting("Android")
    }
}