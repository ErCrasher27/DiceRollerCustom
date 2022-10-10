package com.example.dicerollercustom.ui

data class DiceRollerCustomUiState(
    var result: Int = 1,
    var history: MutableList<Int> = mutableListOf()
)
