package cz.vvoleman.documentgallery.presentation.model.list

import cz.vvoleman.documentgallery.base.presentation.model.PresentationDestination

sealed class ListDestination : PresentationDestination {
    data class Folder(val folderId: String) : ListDestination()
}