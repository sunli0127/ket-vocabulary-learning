package com.ket.vocabulary.core.domain.model

/**
 * 用户领域模型
 */
data class User(
    val id: Int,
    val name: String,
    val avatar: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val lastActiveTime: Long = System.currentTimeMillis(),
    val totalStudyTime: Long = 0,
    val totalWordsLearned: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0
)