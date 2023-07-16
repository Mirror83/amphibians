package com.example.amphibians

import com.example.amphibians.data.NetworkAmphibiansRepository
import com.example.amphibians.fake.FakeAmphibiansApiService
import com.example.amphibians.fake.FakeDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AmphibiansRepositoryTest {
    @Test
    fun networkAmphibiansRepository_getAmphibians_verifyAmphibiansList() = runTest {
        val networkAmphibiansRepository = NetworkAmphibiansRepository(
            retrofitAmphibiansService = FakeAmphibiansApiService()
        )
        assertEquals(networkAmphibiansRepository.getAmphibians(), FakeDataSource.amphibians)
    }
}