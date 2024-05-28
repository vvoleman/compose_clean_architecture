package cz.vvoleman.documentgallery.base.presentation.viewmodel.usecase

import cz.vvoleman.documentgallery.base.domain.usecase.UseCaseExecutor
import kotlinx.coroutines.CoroutineScope

typealias UseCaseExecutorProvider =
        @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor