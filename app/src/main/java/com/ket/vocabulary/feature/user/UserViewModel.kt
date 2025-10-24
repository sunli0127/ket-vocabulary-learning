package com.ket.vocabulary.feature.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ket.vocabulary.core.domain.model.User
import com.ket.vocabulary.core.domain.usecase.GetUsersUseCase
import com.ket.vocabulary.core.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 用户管理ViewModel
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()
    
    init {
        loadUsers()
    }
    
    /**
     * 加载用户列表
     */
    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                getUsersUseCase().collect { users ->
                    _uiState.value = _uiState.value.copy(
                        users = users,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
    
    /**
     * 创建新用户
     */
    fun createUser(userName: String) {
        viewModelScope.launch {
            try {
                val newUser = createUserUseCase(userName)
                _uiState.value = _uiState.value.copy(
                    error = null,
                    message = "用户创建成功"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message
                )
            }
        }
    }
    
    /**
     * 选择用户
     */
    fun selectUser(user: User) {
        _uiState.value = _uiState.value.copy(
            selectedUser = user
        )
    }
    
    /**
     * 清除错误消息
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    
    /**
     * 清除成功消息
     */
    fun clearMessage() {
        _uiState.value = _uiState.value.copy(message = null)
    }
}

/**
 * 用户界面状态
 */
data class UserUiState(
    val users: List<User> = emptyList(),
    val selectedUser: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val message: String? = null
)