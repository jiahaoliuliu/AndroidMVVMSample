package com.jiahaoliuliu.androidmvvmsample.presentation.base

import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article

sealed interface UiState {
    data class Success(val data: List<Article>): UiState
    data class Error(val message: String): UiState
    object Loading: UiState
}