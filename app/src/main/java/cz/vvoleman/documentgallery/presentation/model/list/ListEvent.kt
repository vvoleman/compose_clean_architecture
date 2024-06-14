package cz.vvoleman.documentgallery.presentation.model.list

import cz.vvoleman.documentgallery.domain.model.FolderModel

sealed class ListEvent {
    data class OnFolderDetail(val folder: FolderModel) : ListEvent()
}