package com.jiahaoliuliu.androidmvvmsample.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jiahaoliuliu.androidmvvmsample.presentation.details.NewsDetails
import com.jiahaoliuliu.androidmvvmsample.presentation.main.TopHeadlinesListScreen
import com.jiahaoliuliu.androidmvvmsample.presentation.theme.AndroidMVVMSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidMVVMSampleTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "list") {
                        composable("list") {
                            TopHeadlinesListScreen(navController)
                        }
                        composable("details/{url}") {backStackEntry ->
                            NewsDetails(url = backStackEntry.arguments?.getString("url") ?: "")
                        }
                    }
                }
            }
        }
    }
}
