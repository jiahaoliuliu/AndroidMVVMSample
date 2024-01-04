package com.jiahaoliuliu.androidmvvmsample.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ArticleRemoteData>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ArticleRemoteData>>> = _uiState

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}