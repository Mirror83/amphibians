package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    private val retrofitAmphibiansService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return retrofitAmphibiansService.getAmphibians()
    }

}