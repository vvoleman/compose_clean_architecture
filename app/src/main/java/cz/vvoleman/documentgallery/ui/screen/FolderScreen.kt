package cz.vvoleman.documentgallery.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import cz.vvoleman.documentgallery.domain.model.FolderModel
import cz.vvoleman.documentgallery.presentation.model.folder.FolderEvent
import cz.vvoleman.documentgallery.presentation.model.folder.FolderNavArgs
import cz.vvoleman.documentgallery.presentation.model.folder.FolderViewState
import cz.vvoleman.documentgallery.presentation.viewmodel.FolderViewModel
import cz.vvoleman.documentgallery.ui.navigation.MainNavGraph
import java.time.LocalDateTime

const val FOLDER_ROUTE = "folder"

@Destination(
    route = FOLDER_ROUTE,
    navArgsDelegate = FolderNavArgs::class,
)
@MainNavGraph
@Composable
fun FolderScreen() {
    val viewModel: FolderViewModel = hiltViewModel()

    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onInit()
    }

    if (state == null) {
        return
    }

    FolderScreenContent(state = state!!) {
        viewModel.onEvent(it)
    }
}

@Composable
private fun FolderScreenContent(
    state: FolderViewState,
    onEvent: (FolderEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Folder: ${state.folder.name}", modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            onEvent(FolderEvent.OnReturnBack)
        }) {
            Text(text = "Go back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FolderScreenPreview() {
    val viewState = FolderViewState(
        folder = FolderModel(
            id = "1",
            name = "Folder 1",
            createdAt = LocalDateTime.now(),
            description = "Folder 1 description",
            documents = emptyList()
        )
    )
    FolderScreenContent(state = viewState) {

    }
}