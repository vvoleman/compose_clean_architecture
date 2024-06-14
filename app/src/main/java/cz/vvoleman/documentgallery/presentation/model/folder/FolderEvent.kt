package cz.vvoleman.documentgallery.presentation.model.folder

sealed class FolderEvent {
    data object OnReturnBack : FolderEvent()
}