package cz.vvoleman.documentgallery.base.di

import cz.vvoleman.documentgallery.base.domain.coroutine.CoroutineContextProvider
import cz.vvoleman.documentgallery.base.domain.usecase.UseCaseExecutor
import cz.vvoleman.documentgallery.base.presentation.viewmodel.coroutine.AppCoroutineContextProvider
import cz.vvoleman.documentgallery.base.presentation.viewmodel.usecase.UseCaseExecutorProvider
import cz.vvoleman.documentgallery.base.ui.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseModule {

    @Provides
    fun providesCoroutineContextProvider(): CoroutineContextProvider =
        AppCoroutineContextProvider()

    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider =
        ::UseCaseExecutor

    @Provides
    @Singleton
    fun providesNavigator() = Navigator()

}