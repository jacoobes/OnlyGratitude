package org.soloqueue.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.font.SystemFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    onSaveChanges: () -> Unit,
    onDismiss: () -> Unit,
    textSize: MutableState<TextUnit>,
    fontFamily: MutableState<SystemFontFamily>,
    textAlign: MutableState<TextAlign>
) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Settings", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Font Size")
        Slider(
            value = textSize.value.value,
            onValueChange = { textSize.value = it.sp },
            valueRange = (10f)..(60f),
            steps = 10
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Font Family")
        DropdownMenu(
            expanded = false,
            onDismissRequest = {},
        ) {
            listOf(
                FontFamily.Default,
                FontFamily.Monospace,
                FontFamily.SansSerif,
                FontFamily.Serif,
                FontFamily.Cursive
            ).forEach { family ->
                DropdownMenuItem(
                    text = { Text(family.toString()) },
                    onClick = { fontFamily.value = family }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Text Alignment")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { textAlign.value = TextAlign.Start }) {
                Text("Left")
            }
            TextButton(onClick = { textAlign.value = TextAlign.Center }) {
                Text("Center")
            }
            TextButton(onClick = { textAlign.value = TextAlign.End }) {
                Text("Right")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {
                onSaveChanges()
                onDismiss()
            }) {
                Text("Save")
            }
            TextButton(onClick = {
                onDismiss()
            }
            ) {
                Text("Cancel")
            }
        }
    }
}
