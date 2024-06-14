package cz.vvoleman.documentgallery.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import cz.vvoleman.documentgallery.base.presentation.model.UiState
import cz.vvoleman.documentgallery.presentation.model.list.ListEvent
import cz.vvoleman.documentgallery.presentation.model.list.ListViewState
import cz.vvoleman.documentgallery.presentation.viewmodel.ListViewModel
import cz.vvoleman.documentgallery.ui.navigation.MainNavGraph

const val HOME_ROUTE = "home"

@Destination(
    route = HOME_ROUTE,
)
@MainNavGraph(start = true)
@Composable
fun ListScreen() {
    val viewModel: ListViewModel = hiltViewModel()
    val state by viewModel.viewState.collectAsState()
    val onEvent = { event: ListEvent -> viewModel.onEvent(event) }

    LaunchedEffect(key1 = Unit) {
        viewModel.onInit()
    }

    if (state == null) return

    HomeScreenContent(state = state!!, onEvent = onEvent)
}

@Composable
fun HomeScreenContent(
    state: ListViewState,
    onEvent: (ListEvent) -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.folders is UiState.Loading) {
                Text(text = "Načítám")
                CircularProgressIndicator()
            } else if (state.folders is UiState.Success) {
                val folders = state.folders.data
                for (folder in folders) {
                    Button(
                        onClick = {
                            onEvent(ListEvent.OnFolderDetail(folder))
                        }
                    ) {
                        Text(text = folder.name)
                    }
                }
            }
        }

    }
}