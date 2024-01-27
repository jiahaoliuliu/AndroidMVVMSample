package com.jiahaoliuliu.androidmvvmsample.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jiahaoliuliu.AndroidMVVMSampleApplication.R
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.presentation.main.composable.ErrorView
import com.jiahaoliuliu.androidmvvmsample.presentation.main.composable.IndeterminateCircularIndicator
import com.jiahaoliuliu.androidmvvmsample.presentation.main.composable.TopHeadline

@ExperimentalMaterial3Api
@Composable
fun TopHeadlinesListScreen(navController: NavController, vm: TopHeadlineViewModel = hiltViewModel()) {
    val state by vm.uiState.collectAsState()
    when (state) {
        is UiState.Loading ->
            IndeterminateCircularIndicator()
        // Handling the error
        is UiState.Error -> {
            ErrorView(errorMessage = (state as UiState.Error).message, onClick = vm::retry)
        }
        is UiState.Success -> {
            Results(articlesList = (state as UiState.Success).data, viewModel = vm) {url, title ->
                navController.navigate("details/$url/$title")
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Results(articlesList: List<Article>, viewModel: TopHeadlineViewModel, onClick: (String, String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text= stringResource(id = R.string.app_name), maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.fetchArticlesAndSortByName()
                    }) {
                        Icon(
                            painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Sort items by name"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyColumn (modifier = Modifier.padding(paddingValues)){
            items(articlesList) { article ->
                TopHeadline(article, onClick)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun ListScreenReview() {
    val article1 = Article(
        title = "News Title",
        description = "This is a good news",
        url = "",
        imageUrl = "",
        source = ""
    )
    val article2 = Article(
        title = "News Title 2",
        description = "This is a very good news",
        url = "",
        imageUrl = "",
        source = ""
    )
    val articlesList = listOf(article1, article2)
    Results(articlesList = articlesList, viewModel = hiltViewModel()) {_, _ ->  }
}

