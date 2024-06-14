package cz.vvoleman.documentgallery.base.presentation.mapper

import androidx.lifecycle.SavedStateHandle
import cz.vvoleman.documentgallery.base.presentation.model.PresentationDestination

interface NavigationMapper<DESTINATION : PresentationDestination, ARGS: Any> {
    fun navigate(destination: DESTINATION)

    fun getArgs(savedStateHandle: SavedStateHandle): ARGS? = null
}