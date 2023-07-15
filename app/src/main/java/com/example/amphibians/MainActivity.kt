package com.example.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.AmphibiansApp
import com.example.amphibians.ui.AmphibiansViewModel
import com.example.amphibians.ui.theme.AmphibiansTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansTheme {
                val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
                val nestedScrollConnection = scrollBehaviour.nestedScrollConnection
                Scaffold(topBar = { AmphibiansTopAppBar(scrollBehaviour) }) {
                    Surface(
                        modifier = Modifier
                            .padding(it)
                            .nestedScroll(nestedScrollConnection)
                            .fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val amphibiansViewModel: AmphibiansViewModel = viewModel()
                        AmphibiansApp(amphibiansViewModel.amphibiansUiState, retryHandler = {
                            amphibiansViewModel.getAmphibians()
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    MediumTopAppBar(
        title = {
            Text(
                text = "Amphibians",
                style = MaterialTheme.typography.headlineLarge
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

