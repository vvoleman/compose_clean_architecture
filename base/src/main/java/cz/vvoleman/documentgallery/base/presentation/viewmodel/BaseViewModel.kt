package cz.vvoleman.documentgallery.base.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vvoleman.documentgallery.base.domain.exception.DomainException
import cz.vvoleman.documentgallery.base.domain.usecase.UseCase
import cz.vvoleman.documentgallery.base.presentation.mapper.NavigationMapper
import cz.vvoleman.documentgallery.base.presentation.model.PresentationDestination
import cz.vvoleman.documentgallery.base.presentation.viewmodel.usecase.UseCaseExecutorProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<VIEW_STATE : Any, DESTINATION: PresentationDestination, NOTIFICATION : Any, ARGS: Any>(
    @Suppress("UnusedPrivateProperty")
    protected val navigationMapper: NavigationMapper<DESTINATION, ARGS>,
    protected val savedStateHandle: SavedStateHandle,
    protected val useCaseExecutorProvider: UseCaseExecutorProvider
) : ViewModel() {

    @Suppress("VariableNaming")
    protected abstract val TAG: String

    private val _viewState = MutableStateFlow<VIEW_STATE?>(null)
    val viewState = _viewState.asStateFlow()

    private val _notification = MutableSharedFlow<NOTIFICATION>()
    val notification = _notification.asSharedFlow()

    private val _destination = MutableSharedFlow<DESTINATION>()
    val destination = _destination.asSharedFlow()

    protected val currentViewState: VIEW_STATE
        get() = viewState.value!!

    private val useCaseExecutor by lazy {
        useCaseExecutorProvider(viewModelScope)
    }

//    init {
//        viewModelScope.launch {
//            try {
//                Log.d(TAG, "Initializing view model")
//                onInit()
//            } catch (e: Exception) {
//                Log.e(TAG, "Error during initialization", e)
//            }
//        }
//    }

    open suspend fun onInit() {
        _viewState.value = setupState()
    }

    private suspend fun setupState(): VIEW_STATE {
        return initState()
    }

    protected abstract suspend fun initState(): VIEW_STATE

    protected fun <INPUT, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        useCaseExecutor.execute(useCase, value, onSuccess, onException)
    }

    protected open fun updateViewState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
        Log.d(TAG, "New view state: $newViewState")
    }

    protected fun notify(notification: NOTIFICATION) {
        viewModelScope.launch {
            _notification.emit(notification)
        }
    }

    protected fun navigateTo(destination: DESTINATION) {
        viewModelScope.launch {
            navigationMapper.navigate(destination)
        }
    }
}