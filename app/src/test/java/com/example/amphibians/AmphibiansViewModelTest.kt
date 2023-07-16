package com.example.amphibians

import com.example.amphibians.fake.FakeAmphibiansRepository
import com.example.amphibians.fake.FakeDataSource
import com.example.amphibians.rules.TestDispatcherRule
import com.example.amphibians.ui.AmphibiansUiState
import com.example.amphibians.ui.AmphibiansViewModel
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AmphibiansViewModelTest {
    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun amphibiansViewModels_getAmphibians_verifyAmphibiansUiStateSuccess() = runTest {
        val amphibiansViewModel = AmphibiansViewModel(FakeAmphibiansRepository())
        TestCase.assertEquals(
            amphibiansViewModel.amphibiansUiState,
            AmphibiansUiState.Success(FakeDataSource.amphibians)
        )
    }
}