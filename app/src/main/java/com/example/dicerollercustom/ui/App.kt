package com.example.dicerollercustom.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dicerollercustom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceRollerCustomApp(
    modifier: Modifier = Modifier,
) {
    val viewModel: DiceRollerCustomViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = { TopAppBarDiceRollerCustom(title = stringResource(id = R.string.app_name)) },
        content = {
            ContentApp(
                modifier = Modifier.padding(it),
                numberRolled = uiState.result,
            )
        },
        floatingActionButton = {
            FABRoll(
                roll = {
                    viewModel.updateResultRandom()
                }
            )
        }
    )
}
