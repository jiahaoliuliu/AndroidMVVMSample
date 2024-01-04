package com.jiahaoliuliu.androidmvvmsample.presentation.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable { isExpanded = !isExpanded }
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
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "Background ",
        )

        Text(
            text = article.title,
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
                    Text(
                        article.description,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        article.source,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
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