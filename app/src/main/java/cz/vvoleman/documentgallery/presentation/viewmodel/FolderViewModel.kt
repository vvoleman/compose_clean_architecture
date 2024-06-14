package cz.vvoleman.documentgallery.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import cz.vvoleman.documentgallery.base.presentation.mapper.NavigationMapper
import cz.vvoleman.documentgallery.base.presentation.viewmodel.BaseViewModel
import cz.vvoleman.documentgallery.base.presentation.viewmodel.usecase.UseCaseExecutorProvider
import cz.vvoleman.documentgallery.domain.model.FolderModel
import cz.vvoleman.documentgallery.domain.repository.GetFolderByIdRepository
import cz.vvoleman.documentgallery.presentation.model.folder.FolderDestination
import cz.vvoleman.documentgallery.presentation.model.folder.FolderEvent
import cz.vvoleman.documentgallery.presentation.model.folder.FolderNavArgs
import cz.vvoleman.documentgallery.presentation.model.folder.FolderNotification
import cz.vvoleman.documentgallery.presentation.model.folder.FolderViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(
    private val getFolderByIdRepository: GetFolderByIdRepository,
    navigationMapper: NavigationMapper<FolderDestination, FolderNavArgs>,
    savedStateHandle: SavedStateHandle,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BaseViewModel<FolderViewState, FolderDestination, FolderNotification, FolderNavArgs>(
    navigationMapper,
    savedStateHandle,
    useCaseExecutorProvider
) {

    override val TAG = "FolderViewModel"

    override suspend fun initState(): FolderViewState {
        val args = navigationMapper.getArgs(savedStateHandle)
        requireNotNull(args) { "Args not found" }

        val folder = getFolder(args)

        return FolderViewState(
            folder = folder
        )
    }

    fun onEvent(event: FolderEvent) {
        when (event) {
            FolderEvent.OnReturnBack -> {
                navigateTo(FolderDestination.List)
            }
        }
    }

    private suspend fun getFolder(args: FolderNavArgs): FolderModel {
        val folder = getFolderByIdRepository.getFolderById(args.folderId)

        return requireNotNull(folder) { "Folder not found" }
    }
}