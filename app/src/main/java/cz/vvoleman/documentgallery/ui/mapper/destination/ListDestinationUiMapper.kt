package cz.vvoleman.documentgallery.ui.mapper.destination

import cz.vvoleman.documentgallery.base.ui.DestinationUiMapper
import cz.vvoleman.documentgallery.base.ui.Navigator
import cz.vvoleman.documentgallery.presentation.model.folder.FolderNavArgs
import cz.vvoleman.documentgallery.presentation.model.list.ListDestination
import cz.vvoleman.documentgallery.ui.screen.destinations.FolderScreenDestination

class ListDestinationUiMapper(
    navigator: Navigator
) : DestinationUiMapper<ListDestination, Any>(navigator) {
    override fun navigate(destination: ListDestination) {
        when (destination) {
            is ListDestination.Folder -> {
                val route = FolderScreenDestination(
                    navArgs = FolderNavArgs(
                        folderId = destination.folderId
                    )
                )
                navigator.navigate(route)
            }
        }
    }
}