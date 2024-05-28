package cz.vvoleman.documentgallery.base.domain.usecase

import cz.vvoleman.documentgallery.base.domain.coroutine.CoroutineContextProvider
import kotlinx.coroutines.withContext

abstract class BackgroundExecutingUseCase<REQUEST, RESULT>(
    private val coroutineContextProvider: CoroutineContextProvider
) : UseCase<REQUEST, RESULT> {

    final override suspend fun execute(input: REQUEST, onResult: (RESULT) -> Unit) {
        val result = withContext(coroutineContextProvider.io) {
            executeInBackground(input)
        }

        onResult(result)
    }

    abstract suspend fun executeInBackground(request: REQUEST): RESULT
}