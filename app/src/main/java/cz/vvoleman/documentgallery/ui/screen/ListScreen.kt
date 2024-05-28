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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.vvoleman.documentgallery.base.presentation.model.UiState
import cz.vvoleman.documentgallery.presentation.model.list.ListEvent
import cz.vvoleman.documentgallery.presentation.model.list.ListViewState

@Composable
fun ListScreen(
    state: ListViewState,
    onEvent: (ListEvent) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    onEvent(ListEvent.LoadFolders)
                },
                modifier = Modifier
                    .padding(it)
            ) {
                Text("Load folders")
            }

            Text(text = "State: ${state.folders}")
            if (state.folders is UiState.Loading) {
                Text(text = "Loading...")
                CircularProgressIndicator()
            } else if (state.folders is UiState.Success) {
                for (folder in (state.folders as UiState.Success).data) {
                    Text(folder.name)
                }
            }
        }

    }
}