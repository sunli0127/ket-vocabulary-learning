package com.ket.vocabulary.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户实体类
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val avatar: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val lastActiveTime: Long = System.currentTimeMillis(),
    val totalStudyTime: Long = 0,
    val totalWordsLearned: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0
)