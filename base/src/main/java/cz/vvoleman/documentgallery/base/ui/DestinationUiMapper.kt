package cz.vvoleman.documentgallery.base.ui

import androidx.lifecycle.SavedStateHandle
import cz.vvoleman.documentgallery.base.presentation.mapper.NavigationMapper
import cz.vvoleman.documentgallery.base.presentation.model.PresentationDestination

abstract class DestinationUiMapper<TYPE : PresentationDestination, ARGS: Any>(
    protected val navigator: Navigator
) : NavigationMapper<TYPE, ARGS> {

    override fun getArgs(savedStateHandle: SavedStateHandle): ARGS?
    {
        return null
    }

}