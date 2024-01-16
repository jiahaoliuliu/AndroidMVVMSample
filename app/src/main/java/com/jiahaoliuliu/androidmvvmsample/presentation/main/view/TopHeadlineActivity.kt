package com.jiahaoliuliu.androidmvvmsample.presentation.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.presentation.composable.IndeterminateCircularIndicator
import com.jiahaoliuliu.androidmvvmsample.presentation.composable.TopHeadline
import com.jiahaoliuliu.androidmvvmsample.presentation.main.viewmodel.TopHeadlineViewModel
import com.jiahaoliuliu.androidmvvmsample.presentation.theme.AndroidMVVMSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopHeadlineActivity: AppCompatActivity() {

    private val topHeadlineViewModel: TopHeadlineViewModel by viewModels()
    private val _topHeadlinesList = mutableStateListOf<Article>()
    private val topHeadlinesList: List<Article> = _topHeadlinesList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidMVVMSampleTheme {
                Surface {
                    TopHeadlinesList(articlesList = topHeadlinesList)
                }
            }
        }
        setUpObserver()
    }

    @Composable
    fun TopHeadlinesList(articlesList: List<Article>) {
        var isLoading by remember { mutableStateOf(true)}
        isLoading = articlesList.isEmpty()

        if (isLoading) {
            IndeterminateCircularIndicator()
        }

        LazyColumn {
            items(articlesList) {article ->
                TopHeadline(article)
            }
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineViewModel.uiState.collect {
                    when(it) {
                        is UiState.Success -> {
                            _topHeadlinesList.addAll(it.data)
                        }
                        is UiState.Loading -> {
                            // Do nothing. It is showing by default
                        }
                        is UiState.Error -> {
                            // Handling error
                        }
                    }
                }
            }
        }
    }
}