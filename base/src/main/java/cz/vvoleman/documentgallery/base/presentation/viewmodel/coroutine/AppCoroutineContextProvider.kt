package cz.vvoleman.documentgallery.base.presentation.viewmodel.coroutine

import cz.vvoleman.documentgallery.base.domain.coroutine.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers

class AppCoroutineContextProvider : CoroutineContextProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
}