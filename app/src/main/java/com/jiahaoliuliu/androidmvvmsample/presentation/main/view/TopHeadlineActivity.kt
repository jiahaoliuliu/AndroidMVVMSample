package com.jiahaoliuliu.androidmvvmsample.presentation.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.presentation.composable.IndeterminateCircularIndicator
import com.jiahaoliuliu.androidmvvmsample.presentation.composable.TopHeadline
import com.jiahaoliuliu.androidmvvmsample.presentation.main.viewmodel.TopHeadlineViewModel
import com.jiahaoliuliu.androidmvvmsample.presentation.theme.AndroidMVVMSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidMVVMSampleTheme {
                Surface {
                    TopHeadlinesListScreen()
                }
            }
        }
    }

    @Composable
    fun TopHeadlinesListScreen(vm: TopHeadlineViewModel = hiltViewModel()) {
        val state by vm.uiState.collectAsState()

        when (state) {
            is UiState.Loading ->
                IndeterminateCircularIndicator()
            is UiState.Error -> {}
                // Handling the error
            is UiState.Success -> Results(articlesList = (state as UiState.Success).data)
        }
    }

    @Composable
    fun Results(articlesList: List<Article>) {
        LazyColumn {
            items(articlesList) { article ->
                TopHeadline(article)
            }
        }
    }
}