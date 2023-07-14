package com.example.amphibians.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibians.AmphibiansTopAppBar
import com.example.amphibians.data.Amphibian
import com.example.amphibians.ui.theme.AmphibiansTheme

val amphibians = List(10) { Amphibian("Amphibian $it", "Description $it", "") }

@Composable
fun AmphibiansApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        AmphibiansList(amphibians = amphibians)
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
            Spacer(modifier.height(8.dp))
            Text(amphibian.description)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AmphibiansAppPreview() {
    AmphibiansTheme {
        Scaffold(topBar = { AmphibiansTopAppBar() }) {
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AmphibiansApp()
            }
        }
    }
}