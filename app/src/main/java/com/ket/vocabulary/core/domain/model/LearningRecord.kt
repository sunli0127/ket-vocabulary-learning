package com.ket.vocabulary.core.domain.model

/**
 * 学习记录领域模型
 */
data class LearningRecord(
    val id: Int,
    val userId: Int,
    val vocabularyId: Int,
    val isCorrect: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val nextReviewTime: Long,
    val reviewCount: Int = 0,
    val masteryLevel: Int = 0,
    val responseTime: Long? = null,
    val reviewType: String = "learning"
)