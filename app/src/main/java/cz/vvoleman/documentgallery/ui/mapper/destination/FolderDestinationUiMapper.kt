package cz.vvoleman.documentgallery.ui.mapper.destination

import androidx.lifecycle.SavedStateHandle
import cz.vvoleman.documentgallery.base.ui.DestinationUiMapper
import cz.vvoleman.documentgallery.base.ui.Navigator
import cz.vvoleman.documentgallery.presentation.model.folder.FolderDestination
import cz.vvoleman.documentgallery.presentation.model.folder.FolderNavArgs
import cz.vvoleman.documentgallery.ui.screen.destinations.ListScreenDestination
import cz.vvoleman.documentgallery.ui.screen.navArgs

class FolderDestinationUiMapper(
    navigator: Navigator
) : DestinationUiMapper<FolderDestination, FolderNavArgs>(navigator) {
    override fun navigate(destination: FolderDestination) {
        when (destination) {
            is FolderDestination.List -> {
                navigator.navigate(ListScreenDestination)
            }
        }
    }

    override fun getArgs(savedStateHandle: SavedStateHandle): FolderNavArgs {
        return savedStateHandle.navArgs() as FolderNavArgs
    }
}