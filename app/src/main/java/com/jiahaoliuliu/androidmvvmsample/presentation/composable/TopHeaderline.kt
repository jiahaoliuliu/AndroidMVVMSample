package com.jiahaoliuliu.androidmvvmsample.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jiahaoliuliu.AndroidMVVMSampleApplication.R
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.presentation.theme.AndroidMVVMSampleTheme

@Composable
fun TopHeadline(article: Article) {

    Column(
        modifier = Modifier
            .padding(all = 8.dp)
    )
    {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.imageUrl)
                .error(R.drawable.image_place_holder)
                .placeholder(R.drawable.image_place_holder)
                .crossfade(true)
                .build(),
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview
@Composable
fun PreviewTopHeadline() {
    val article = Article(
        title = "Apple jobs: Career opportunities are available for employees from 'all walks of life'",
        description = "There are diverse career opportunities at Apple. Find out how you can get a job and get hired as part of Apple's work environment.",
        url = "https://www.businessinsider.com/apple-jobs-careers-hiring-opportunities",
        imageUrl = "https://i.insider.com/65943f1fec62ab5daf7ff600?width=1200&format=jpeg",
        source = "Business insider"
    )

    AndroidMVVMSampleTheme {
        Surface {
            TopHeadline(article = article)
        }
    }
}