package cz.vvoleman.documentgallery.di

import cz.vvoleman.documentgallery.base.presentation.mapper.NavigationMapper
import cz.vvoleman.documentgallery.base.ui.Navigator
import cz.vvoleman.documentgallery.presentation.model.folder.FolderDestination
import cz.vvoleman.documentgallery.presentation.model.folder.FolderNavArgs
import cz.vvoleman.documentgallery.presentation.model.list.ListDestination
import cz.vvoleman.documentgallery.ui.mapper.destination.FolderDestinationUiMapper
import cz.vvoleman.documentgallery.ui.mapper.destination.ListDestinationUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    fun providesListDestinationUiMapper(
        navigator: Navigator
    ): NavigationMapper<ListDestination, Any> {
        return ListDestinationUiMapper(navigator)
    }

    @Provides
    fun providesFolderDestinationUiMapper(
        navigator: Navigator
    ): NavigationMapper<FolderDestination, FolderNavArgs> {
        return FolderDestinationUiMapper(navigator)
    }

}