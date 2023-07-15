package com.example.amphibians.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.AmphibiansTopAppBar
import com.example.amphibians.R
import com.example.amphibians.data.Amphibian
import com.example.amphibians.ui.theme.AmphibiansTheme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AmphibiansApp(
    amphibiansUiState: AmphibiansUiState,
    retryHandler: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedContent(targetState = amphibiansUiState) { targetState ->
        when (targetState) {
            is AmphibiansUiState.Success -> AmphibiansList(targetState.amphibians, modifier)
            is AmphibiansUiState.Loading -> LoadingScreen(modifier)
            is AmphibiansUiState.Error -> ErrorScreen(modifier, retryHandler)
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Loading")
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, retryHandler: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Failed to load amphibians", modifier.padding(8.dp))
        Button(onClick = retryHandler) {
            Text("Retry")
        }
    }
}

@Composable
fun AmphibiansList(amphibians: List<Amphibian>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(amphibians, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibianCard(amphibian)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(amphibian.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier.height(4.dp))
            Text(amphibian.type, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier.height(4.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .placeholder(R.drawable.baseline_sync_24)
                    .error(R.drawable.baseline_error_24)
                    .crossfade(true)
                    .build()
                ,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1.5f)
                    .fillMaxWidth()
            )
            Spacer(modifier.height(8.dp))
            Text(amphibian.description)
        }
    }
}

val amphibians = List(10) { Amphibian("Amphibian $it", "Type $it", "Description $it", "") }

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AmphibiansAppPreview() {
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
                AmphibiansApp(AmphibiansUiState.Success(amphibians), {})
            }
        }
    }
}