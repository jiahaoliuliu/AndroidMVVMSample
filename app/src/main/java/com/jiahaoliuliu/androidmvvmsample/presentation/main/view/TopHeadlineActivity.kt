package com.jiahaoliuliu.androidmvvmsample.presentation.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jiahaoliuliu.AndroidMVVMSampleApplication.R
import com.jiahaoliuliu.androidmvvmsample.AndroidMVVMSampleApplication
import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import com.jiahaoliuliu.androidmvvmsample.data.model.SourceRemoteData
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
    private val _topHeadlinesList = mutableStateListOf<ArticleRemoteData>()
    private val topHeadlinesList: List<ArticleRemoteData> = _topHeadlinesList

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
    fun TopHeadlinesList(articlesList: List<ArticleRemoteData>) {
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

    @Composable
    fun TopHeadline(articleRemoteData: ArticleRemoteData) {
        var isExpanded by remember { mutableStateOf(false)}

        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .clickable { isExpanded = !isExpanded }
        )
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(articleRemoteData.urlToImage)
                    .error(R.drawable.image_place_holder)
                    .placeholder(R.drawable.image_place_holder)
                    .crossfade(true)
                    .build(),
                contentDescription = articleRemoteData.title,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                label = "Background ",
            )

            Text(
                text = articleRemoteData.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                )
            if (isExpanded) {
                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shadowElevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Column {
                        articleRemoteData.description?.let {
                            Text(
                                articleRemoteData.description,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        articleRemoteData.source?.let { source ->
                            source.name?.let { sourceName ->
                                Text(
                                    sourceName,
                                    style = MaterialTheme.typography.bodySmall
                                    )
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewTopHeadline() {
        val sourceRemoteData = SourceRemoteData(
            id = "business-insider",
            name = "Business Insider"
        )
        val articleRemoteData = ArticleRemoteData(
            title = "Apple jobs: Career opportunities are available for employees from 'all walks of life'",
            description = "There are diverse career opportunities at Apple. Find out how you can get a job and get hired as part of Apple's work environment.",
            url = "https://www.businessinsider.com/apple-jobs-careers-hiring-opportunities",
            urlToImage = "https://i.insider.com/65943f1fec62ab5daf7ff600?width=1200&format=jpeg",
            source = sourceRemoteData
        )

        AndroidMVVMSampleTheme {
            Surface {
                TopHeadline(articleRemoteData = articleRemoteData)
            }
        }
    }

    @Preview
    @Composable
    fun IndeterminateCircularIndicator() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                strokeWidth = 5.dp
            )
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
        DaggerActivityComponent.builder()
            .applicationComponent((application as AndroidMVVMSampleApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}