package com.jiahaoliuliu.androidmvvmsample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class DiceRollActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create a ViewModel the first time the system calls an activity's onCreate method
        // Re-created activities receive the same DiceRollViewModel instance created by the first activity

        // Use the `by viewModels()` Kotlin property delegate
        // from the activity-ktx artifact
        val viewModel: DiceRollViewModel by viewModels()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // Update UI elements
                }
            }
        }
    }
}