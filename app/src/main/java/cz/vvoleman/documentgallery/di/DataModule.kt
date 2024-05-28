package cz.vvoleman.documentgallery.di

import cz.vvoleman.documentgallery.data.repository.FolderRepository
import cz.vvoleman.documentgallery.domain.repository.GetFoldersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesFolderRepository() = FolderRepository()

    @Provides
    fun providesGetFoldersRepository(
        folderRepository: FolderRepository
    ): GetFoldersRepository = folderRepository

}