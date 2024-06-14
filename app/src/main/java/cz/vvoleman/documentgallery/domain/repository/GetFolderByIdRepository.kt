package cz.vvoleman.documentgallery.domain.repository

import cz.vvoleman.documentgallery.domain.model.FolderModel

interface GetFolderByIdRepository {

    suspend fun getFolderById(id: String): FolderModel?

}