package com.jiahaoliuliu.androidmvvmsample.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.utils.AppConstant.COUNTRY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val retrieveTopHeadlineUseCase: RetrieveTopHeadlineUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    // Load data from a suspend fun and mutate state
    init {
        fetchTopHeadlines()
    }

    fun retry() {
        _uiState.value = UiState.Loading
        fetchTopHeadlines()
    }
    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            retrieveTopHeadlineUseCase(COUNTRY)
                .onSuccess {
                    articlesList ->
                        _uiState.value = UiState.Success(articlesList)
                }
                .onFailure {
                    _uiState.value = UiState.Error(it.toString())
                }
        }
    }
}