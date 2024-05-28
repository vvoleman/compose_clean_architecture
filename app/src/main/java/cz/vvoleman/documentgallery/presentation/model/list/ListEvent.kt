package cz.vvoleman.documentgallery.presentation.model.list

sealed class ListEvent {
    data object LoadFolders : ListEvent()
}