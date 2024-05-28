package cz.vvoleman.documentgallery.domain.repository

import cz.vvoleman.documentgallery.domain.model.FolderModel

interface GetFoldersRepository {

    suspend fun getFolders(): List<FolderModel>

}