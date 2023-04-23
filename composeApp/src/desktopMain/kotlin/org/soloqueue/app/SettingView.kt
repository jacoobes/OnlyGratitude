@file:OptIn(ExperimentalMaterial3Api::class)

package org.soloqueue.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowDropDown
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlignCenter
import compose.icons.feathericons.AlignLeft
import compose.icons.feathericons.AlignRight

@Composable
fun SettingsScreen(
    onSaveChanges: () -> Unit,
    onDismiss: () -> Unit,
    textSize: MutableState<TextUnit>,
    fontFamily: MutableState<SystemFontFamily>,
    textAlign: MutableState<TextAlign>
) {
    val expanded = remember { mutableStateOf(false) }
    val default = FontFamily.Default
    val monospace = FontFamily.Monospace
    val serif = FontFamily.Serif
    val sansSerif = FontFamily.SansSerif
    val cursive = FontFamily.Cursive
    Column(
        Modifier
            .fillMaxWidth()
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
        Box(
            modifier = Modifier.clickable(onClick = { expanded.value = !expanded.value })
        ) {
            Text(
                fontFamily.value.toString().substringAfter("FontFamily."),
                modifier = Modifier.padding(end = 16.dp)
            )
            Icon(
                Icons.Sharp.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                //modifier = Modifier.align(Alignment.End)
            ) {
                listOf(
                    default,
                    monospace,
                    sansSerif,
                    serif,
                    cursive
                ).forEach { family ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                family.toString().substringAfter("FontFamily.")
                            )
                        },
                        modifier = Modifier.padding(start = 3.dp, end = 3.dp),
                        onClick = { fontFamily.value = family }
                    )
                }
            }
        }
        Text("Text Alignment")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(.5f)
        ) {
            FilledTonalIconButton(onClick = { textAlign.value = TextAlign.Start }) {
                Icon(FeatherIcons.AlignLeft, null)
            }
            FilledTonalIconButton(onClick = { textAlign.value = TextAlign.Center }) {
                Icon(FeatherIcons.AlignCenter, null)
            }
            FilledTonalIconButton(onClick = { textAlign.value = TextAlign.End }) {
                Icon(FeatherIcons.AlignRight, null)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                onSaveChanges()
                onDismiss()
            }) {
                Icon(Icons.Sharp.Check, null)
            }
            IconButton(
                onClick = { onDismiss() }, content = {
                    Icon(Icons.Sharp.Close, null)
                }
            )
        }
    }

}

