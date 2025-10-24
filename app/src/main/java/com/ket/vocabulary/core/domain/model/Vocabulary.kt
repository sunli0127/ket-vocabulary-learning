package com.ket.vocabulary.core.domain.model

/**
 * 词汇领域模型
 */
data class Vocabulary(
    val id: Int,
    val word: String,
    val phonetic: String,
    val meaning: String,
    val category: String,
    val example: String?,
    val difficulty: Int = 1,
    val frequencyRank: Int? = null,
    val createdAt: Long = System.currentTimeMillis()
)