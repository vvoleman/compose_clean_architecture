package cz.vvoleman.documentgallery.presentation.model.list

import cz.vvoleman.documentgallery.base.presentation.model.UiState
import cz.vvoleman.documentgallery.domain.model.FolderModel

data class ListViewState(
    val folders: UiState<List<FolderModel>>? = null
)
