package cz.vvoleman.documentgallery.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import cz.vvoleman.documentgallery.base.presentation.model.UiState
import cz.vvoleman.documentgallery.base.presentation.viewmodel.BaseViewModel
import cz.vvoleman.documentgallery.base.presentation.viewmodel.usecase.UseCaseExecutorProvider
import cz.vvoleman.documentgallery.domain.model.FolderModel
import cz.vvoleman.documentgallery.domain.repository.GetFoldersRepository
import cz.vvoleman.documentgallery.presentation.model.list.ListEvent
import cz.vvoleman.documentgallery.presentation.model.list.ListNotification
import cz.vvoleman.documentgallery.presentation.model.list.ListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getFoldersRepository: GetFoldersRepository,
    savedStateHandle: SavedStateHandle,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<ListViewState, ListNotification>(savedStateHandle, useCaseExecutorProvider) {

    override val TAG: String = "ListViewModel"

    override suspend fun initState(): ListViewState {
        return ListViewState()
    }

    fun onEvent(event: ListEvent) {
        Log.d(TAG, "onEvent: $event")
        when (event) {
            is ListEvent.LoadFolders -> onFolderLoad()
        }
    }

    fun onFolderLoad() = viewModelScope.launch {
        updateViewState(
            currentViewState.copy(
                folders = UiState.Loading
            )
        )

        // Sleep for 2 seconds to simulate loading
        delay(2000)
        val folders = getFoldersRepository.getFolders()

        updateViewState(
            currentViewState.copy(
                folders = UiState.Success(folders)
            )
        )
    }
}