package com.ket.vocabulary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ket.vocabulary.core.data.service.DataInitializationService
import com.ket.vocabulary.feature.user.UserSelectionScreen
import com.ket.vocabulary.feature.learning.LearningScreen
import com.ket.vocabulary.feature.review.ReviewScreen
import com.ket.vocabulary.feature.statistics.StatisticsScreen
import com.ket.vocabulary.feature.settings.SettingsScreen
import com.ket.vocabulary.feature.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 主活动
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var dataInitializationService: DataInitializationService
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 初始化应用数据
        lifecycleScope.launch {
            dataInitializationService.initializeAppData()
        }
        
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KetVocabularyApp()
                }
            }
        }
    }
}

/**
 * 主应用组件
 */
@Composable
fun KetVocabularyApp() {
    var currentUserId by remember { mutableStateOf<Int?>(null) }
    var currentScreen by remember { mutableStateOf<Screen>(Screen.UserSelection) }
    
    when (currentScreen) {
        Screen.UserSelection -> {
            UserSelectionScreen(
                onUserSelected = { userId ->
                    currentUserId = userId
                    currentScreen = Screen.Main
                },
                onCreateUser = {
                    // 创建用户逻辑
                }
            )
        }
        Screen.Main -> {
            MainScreen(
                userId = currentUserId!!,
                onBack = {
                    currentUserId = null
                    currentScreen = Screen.UserSelection
                },
                onNavigateToLearning = {
                    currentScreen = Screen.Learning
                },
                onNavigateToReview = {
                    currentScreen = Screen.Review
                },
                onNavigateToStatistics = {
                    currentScreen = Screen.Statistics
                },
                onNavigateToSettings = {
                    currentScreen = Screen.Settings
                }
            )
        }
        Screen.Learning -> {
            LearningScreen(
                userId = currentUserId!!,
                onBack = {
                    currentScreen = Screen.Main
                }
            )
        }
        Screen.Review -> {
            ReviewScreen(
                userId = currentUserId!!,
                onBack = {
                    currentScreen = Screen.Main
                }
            )
        }
        Screen.Statistics -> {
            StatisticsScreen(
                userId = currentUserId!!,
                onBack = {
                    currentScreen = Screen.Main
                }
            )
        }
        Screen.Settings -> {
            SettingsScreen(
                onBack = {
                    currentScreen = Screen.Main
                }
            )
        }
    }
}

/**
 * 应用屏幕枚举
 */
sealed class Screen {
    object UserSelection : Screen()
    object Main : Screen()
    object Learning : Screen()
    object Review : Screen()
    object Statistics : Screen()
    object Settings : Screen()
}