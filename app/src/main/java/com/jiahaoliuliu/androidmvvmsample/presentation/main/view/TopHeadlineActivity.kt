package com.jiahaoliuliu.androidmvvmsample.presentation.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import com.jiahaoliuliu.androidmvvmsample.AndroidMVVMSampleApplication
import com.jiahaoliuliu.androidmvvmsample.data.model.Article
import com.jiahaoliuliu.androidmvvmsample.data.model.Source
import com.jiahaoliuliu.androidmvvmsample.di.component.DaggerActivityComponent
import com.jiahaoliuliu.androidmvvmsample.di.module.ActivityModule
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
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
        LazyColumn {
            items(articlesList) {article ->
                TopHeadline(article)
            }
        }
    }

    @Composable
    fun TopHeadline(article: Article) {
        Column {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = article.title,
            )
            Text(article.title)
        }
    }

    @Preview
    @Composable
    fun PreviewTopHeadline() {
        val source = Source(
            id = "business-insider",
            name = "Business Insider"
        )
        val article = Article(
            title = "Apple jobs: Career opportunities are available for employees from 'all walks of life'",
            description = "There are diverse career opportunities at Apple. Find out how you can get a job and get hired as part of Apple's work environment.",
            url = "https://www.businessinsider.com/apple-jobs-careers-hiring-opportunities",
            urlToImage = "https://i.insider.com/65943f1fec62ab5daf7ff600?width=1200&format=jpeg",
            source = source
        )

        AndroidMVVMSampleTheme {
            Surface {
                TopHeadline(article = article)
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
                            // Show progress bar
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
        DaggerActivityComponent.builder()
            .applicationComponent((application as AndroidMVVMSampleApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}