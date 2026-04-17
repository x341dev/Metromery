package dev.x341.metromery.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.x341.metromery.MetromeryViewModel

private val difficulties = listOf(
    1 to "Easy",
    2 to "Normal",
    3 to "Hard"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToGame: () -> Unit,
    onNavigateToCards: () -> Unit,
    viewModel: MetromeryViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedLabel = difficulties.first { it.first == viewModel.difficulty }.second

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Screen", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            TextField(
                value = selectedLabel,
                onValueChange = {},
                readOnly = true,
                label = { Text("Difficulty") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .width(200.dp)
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                difficulties.forEach { (value, label) ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            viewModel.modifyDifficulty(value)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(onClick = onNavigateToGame) { Text("Go to Game") }

        Spacer(Modifier.height(24.dp))
        Button(onClick = onNavigateToCards) { Text("See all cards") }
    }
}