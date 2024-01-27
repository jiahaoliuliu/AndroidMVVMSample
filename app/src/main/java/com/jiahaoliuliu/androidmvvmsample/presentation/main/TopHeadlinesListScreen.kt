package com.jiahaoliuliu.androidmvvmsample.presentation.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.presentation.base.UiState
import com.jiahaoliuliu.androidmvvmsample.presentation.main.composable.ErrorView
import com.jiahaoliuliu.androidmvvmsample.presentation.main.composable.IndeterminateCircularIndicator
import com.jiahaoliuliu.androidmvvmsample.presentation.main.composable.TopHeadline

@Composable
fun TopHeadlinesListScreen(navController: NavController, vm: TopHeadlineViewModel = hiltViewModel()) {
    val state by vm.uiState.collectAsState()

    when (state) {
        is UiState.Loading ->
            IndeterminateCircularIndicator()
        is UiState.Error -> {
            ErrorView(errorMessage = (state as UiState.Error).message, onClick = vm::retry)
        }
        // Handling the error
        is UiState.Success -> Results(articlesList = (state as UiState.Success).data) {url, title ->
            navController.navigate("details/$url/$title")
        }
    }
}

@Composable
fun Results(articlesList: List<Article>, onClick: (String, String) -> Unit) {
    LazyColumn {
        items(articlesList) { article ->
            TopHeadline(article, onClick)
        }
    }
}

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
    Results(articlesList = articlesList) {_, _ ->  }
}

