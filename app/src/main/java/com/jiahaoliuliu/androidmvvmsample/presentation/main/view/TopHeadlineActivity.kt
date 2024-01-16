package com.jiahaoliuliu.androidmvvmsample.presentation.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.presentation.composable.TopHeadlinesList
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