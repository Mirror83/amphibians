package com.example.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.data.Amphibian
import com.example.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Loading : AmphibiansUiState
    object Error : AmphibiansUiState
}

class AmphibiansViewModel: ViewModel() {
    var amphibiansUiState : AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = try {
                val retrofitService = AmphibiansApi.retrofitService
                val amphibians = retrofitService.getAmphibians()
                AmphibiansUiState.Success(amphibians)

            } catch (e: IOException) {
                AmphibiansUiState.Error

            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    init {
        getAmphibians()
    }
}