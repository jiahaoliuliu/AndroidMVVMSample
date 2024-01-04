package com.jiahaoliuliu.androidmvvmsample.presentation.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
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
import com.jiahaoliuliu.androidmvvmsample.AndroidMVVMSampleApplication
import com.jiahaoliuliu.androidmvvmsample.di.component.DaggerPresentationComponent
import com.jiahaoliuliu.androidmvvmsample.di.module.PresentationModule
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.presentation.composable.IndeterminateCircularIndicator
import com.jiahaoliuliu.androidmvvmsample.presentation.composable.TopHeadline
import com.jiahaoliuliu.androidmvvmsample.presentation.main.viewmodel.TopHeadlineViewModel
import com.jiahaoliuliu.androidmvvmsample.presentation.theme.AndroidMVVMSampleTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineActivity: AppCompatActivity() {

    @Inject
    lateinit var topHeadlineViewModel: TopHeadlineViewModel
    private val _topHeadlinesList = mutableStateListOf<Article>()
    private val topHeadlinesList: List<Article> = _topHeadlinesList

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
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

    private fun injectDependencies() {
        DaggerPresentationComponent.builder()
            .applicationComponent((application as AndroidMVVMSampleApplication).applicationComponent)
            .presentationModule(PresentationModule(this)).build().inject(this)
    }
}