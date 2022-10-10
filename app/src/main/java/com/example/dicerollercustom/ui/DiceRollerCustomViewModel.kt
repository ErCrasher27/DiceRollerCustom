package com.example.dicerollercustom.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DiceRollerCustomViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DiceRollerCustomUiState())
    val uiState: StateFlow<DiceRollerCustomUiState> = _uiState

    fun updateResultRandom() {
        _uiState.update {
            it.copy(
                result = rollRandom()
            )
        }
    }

    fun updateHistory(result: Int) {
        _uiState.value.history.add(result)
    }

    private fun rollRandom(): Int {
        return (1..6).random()
    }

    fun clearHistory() {
        _uiState.value.history.clear()
    }
}

