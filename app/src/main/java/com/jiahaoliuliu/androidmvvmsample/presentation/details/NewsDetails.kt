package com.jiahaoliuliu.androidmvvmsample.presentation.details

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsDetails (modifier: Modifier = Modifier.fillMaxSize(), url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        },
        update = { webView ->
            val urlDecoded = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())
            webView.loadUrl(urlDecoded)
        }
    )
}