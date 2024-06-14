package cz.vvoleman.documentgallery.presentation.model.folder

import cz.vvoleman.documentgallery.base.presentation.model.PresentationDestination

sealed class FolderDestination : PresentationDestination {
    data object List : FolderDestination()
}
