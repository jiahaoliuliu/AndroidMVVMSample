package com.jiahaoliuliu.androidmvvmsample.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadlineViewModel(
    private val retrieveTopHeadlineUseCase: RetrieveTopHeadlineUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            retrieveTopHeadlineUseCase(COUNTRY)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}