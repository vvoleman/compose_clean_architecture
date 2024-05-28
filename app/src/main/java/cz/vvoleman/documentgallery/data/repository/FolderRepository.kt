package cz.vvoleman.documentgallery.data.repository

import cz.vvoleman.documentgallery.domain.model.DocumentModel
import cz.vvoleman.documentgallery.domain.model.FolderModel
import cz.vvoleman.documentgallery.domain.repository.GetFoldersRepository
import java.time.LocalDateTime
import kotlin.random.Random

class FolderRepository(

) : GetFoldersRepository {

    override suspend fun getFolders(): List<FolderModel> {
        return listOf(
            FolderModel(
                id = "1",
                name = "Folder 1",
                createdAt = LocalDateTime.now().minusDays(Random.nextLong(1, 1000)),
                description = "Folder 1 description",
                documents = listOf(
                    DocumentModel(
                        id = "1",
                        createdAt = LocalDateTime.now().minusDays(Random.nextLong(1, 1000)),
                        path = "path/to/document1"
                    ),
                    DocumentModel(
                        id = "2",
                        createdAt = LocalDateTime.now().minusDays(Random.nextLong(1, 1000)),
                        path = "path/to/document2"
                    )
                )
            ),
        )

    }
}