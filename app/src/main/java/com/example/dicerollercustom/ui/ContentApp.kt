package com.example.dicerollercustom.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropRotate
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dicerollercustom.R

@Composable
fun ContentApp(
    modifier: Modifier = Modifier,
    numberRolled: Int,
    history: MutableList<Int>,
    clearHistory: () -> Unit
) {
    val imageResource = getImageResourceByResult(numberRolled)
    val stringResource = getStringResourceByResult(numberRolled)
    val randomColor = getColorByResult(numberRolled)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShowDice(dice = imageResource, numberRolled = numberRolled)
        Divider(
            modifier = Modifier
                .padding(top = 40.dp, start = 60.dp, end = 60.dp)
                .height(2.dp),
            color = randomColor
        )
        ShowPhrase(phrase = stringResource)
        HistoryRoll(history = history)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = clearHistory,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
        ) {
            Text(text = stringResource(R.string.clear_history))
        }
    }
}

@Composable
fun FABRoll(
    modifier: Modifier = Modifier,
    roll: () -> Unit,
) {
    FloatingActionButton(
        onClick = roll,
        containerColor = MaterialTheme.colorScheme.primary,
        content = {
            Icon(
                imageVector = Icons.Default.CropRotate,
                contentDescription = stringResource(id = R.string.roll)
            )
        })
}

@Composable
fun ShowDice(
    modifier: Modifier = Modifier,
    dice: Int,
    numberRolled: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = dice),
            contentDescription = null
        )
        Text(text = numberRolled.toString(), style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ShowPhrase(
    modifier: Modifier = Modifier,
    @StringRes phrase: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(40.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = phrase),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun HistoryRoll(
    modifier: Modifier = Modifier,
    history: MutableList<Int>
) {
    Row(
        modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp)
            .clip(shape = MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        history.forEach {
            HistoryItem(dice = it)
        }
    }
}

@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    dice: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.size(80.dp),
            painter = painterResource(id = getImageResourceByResult(dice)),
            contentDescription = null
        )
        Text(text = dice.toString())
    }
}

private fun getImageResourceByResult(result: Int): Int {
    return when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

private fun getStringResourceByResult(result: Int): Int {
    return when (result) {
        1 -> R.string.dice_1
        2 -> R.string.dice_2
        3 -> R.string.dice_3
        4 -> R.string.dice_4
        5 -> R.string.dice_5
        else -> R.string.dice_6
    }
}

private fun getColorByResult(result: Int): Color {
    return when (result) {
        1 -> Color.Red
        2 -> Color.Yellow
        3 -> Color.Blue
        4 -> Color.Magenta
        5 -> Color.Green
        else -> Color.Cyan
    }
}