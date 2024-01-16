package com.jiahaoliuliu.androidmvvmsample.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.utils.AppConstant.COUNTRY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val retrieveTopHeadlineUseCase: RetrieveTopHeadlineUseCase) : ViewModel() {

//    private val _uiState = MutableStateFlow<Result<UiState>>(Result<UiState.Loading>)
//    val uiState: StateFlow<Result<UiState>> = _uiState
//
//    private val _myUiState = MutableStateFlow<Result<UiState>>(Result.Loading)
//    val myUiState: StateFlow<Result<UiState>> = _myUiState

    // Load data from a suspend fun and mutate state
//    init {
//        viewModelScope.launch {
//            val result = ...
//            _myUiState.value = result
//        }
//    }
//    init {
//        fetchTopHeadlines()
//    }

//    val result: StateFlow<UiState>> = flow {
//        emit(retrieveTopHeadlineUseCase(COUNTRY))
//    }.stateIn(
//        scope = viewModelScope,
//        started = WhileSubscribed(5000), // Or Lazily because it's a one-shot
//        initialValue = UiState.Loading
//    )

    val newsState: StateFlow<UiState> =
        retrieveTopHeadlineUseCase(COUNTRY)
            .map(UiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UiState.Loading,
            )

//    private fun fetchTopHeadlines() {
//        viewModelScope.launch {
//            retrieveTopHeadlineUseCase(COUNTRY)
//                .catch { e ->
//                    _uiState.value = UiState.Error(e.toString())
//                }
//                .collect {
//                    _uiState.value = UiState.Success(it)
//                }
//        }
//    }
}