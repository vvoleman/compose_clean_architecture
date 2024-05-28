package cz.vvoleman.documentgallery.domain.model

import java.time.LocalDateTime

data class FolderModel(
    val id: String,
    val name: String,
    val createdAt: LocalDateTime,
    val description: String?,
    val documents: List<DocumentModel>
)
