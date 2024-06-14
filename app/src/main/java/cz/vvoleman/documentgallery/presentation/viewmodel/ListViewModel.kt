package cz.vvoleman.documentgallery.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import cz.vvoleman.documentgallery.base.presentation.mapper.NavigationMapper
import cz.vvoleman.documentgallery.base.presentation.model.UiState
import cz.vvoleman.documentgallery.base.presentation.viewmodel.BaseViewModel
import cz.vvoleman.documentgallery.base.presentation.viewmodel.usecase.UseCaseExecutorProvider
import cz.vvoleman.documentgallery.domain.model.FolderModel
import cz.vvoleman.documentgallery.domain.repository.GetFoldersRepository
import cz.vvoleman.documentgallery.presentation.model.list.ListDestination
import cz.vvoleman.documentgallery.presentation.model.list.ListEvent
import cz.vvoleman.documentgallery.presentation.model.list.ListNotification
import cz.vvoleman.documentgallery.presentation.model.list.ListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getFoldersRepository: GetFoldersRepository,
    navigationMapper: NavigationMapper<ListDestination, Any>,
    savedStateHandle: SavedStateHandle,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<ListViewState, ListDestination, ListNotification, Any>(
    navigationMapper,
    savedStateHandle,
    useCaseExecutorProvider
) {

    override val TAG: String = "ListViewModel"

    override suspend fun initState(): ListViewState {
        return ListViewState(
            folders = UiState.Loading
        )
    }

    override suspend fun onInit() {
        super.onInit()

        viewModelScope.launch {
            val folders = getFolders()
            updateViewState(currentViewState.copy(folders = folders))
        }
    }

    fun onEvent(event: ListEvent) {
        Log.d(TAG, "onEvent: $event")
        when (event) {
            is ListEvent.OnFolderDetail -> onFolderDetail(event.folder)
        }
    }

    private fun onFolderDetail(model: FolderModel) {
        navigateTo(ListDestination.Folder(model.id))
    }

    private suspend fun getFolders(): UiState<List<FolderModel>> {
        delay(2000)
        val folders = getFoldersRepository.getFolders()

        return UiState.Success(folders)
    }
}