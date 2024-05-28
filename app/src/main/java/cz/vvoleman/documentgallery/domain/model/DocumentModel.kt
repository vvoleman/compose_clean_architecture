package cz.vvoleman.documentgallery.domain.model

import java.time.LocalDateTime

data class DocumentModel(
    val id: String,
    val createdAt: LocalDateTime,
    val path: String,
)
