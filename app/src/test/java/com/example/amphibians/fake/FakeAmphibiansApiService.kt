package com.example.amphibians.fake

import com.example.amphibians.data.Amphibian
import com.example.amphibians.network.AmphibiansApiService

class FakeAmphibiansApiService : AmphibiansApiService {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibians
    }
}