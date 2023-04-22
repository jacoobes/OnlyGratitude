package org.soloqueue.app
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.font.SystemFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun JournalView(textContent: MutableState<String>) {
    //Create a File Object (Dummy Path)
    var fontSize = remember { mutableStateOf(16.sp) }
    var fontFamily = remember { mutableStateOf<SystemFontFamily>(FontFamily.Cursive) }
    var textAlign = remember { mutableStateOf(TextAlign.Start) }

    var showSettings by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = textContent.value,
        onValueChange = {
            textContent.value = it
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.inverseSurface,
            fontSize = fontSize.value,
            fontFamily = fontFamily.value,
            textAlign = textAlign.value,
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
    )
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            if (!showSettings) {
                IconButton(onClick = {
                    showSettings = true
                }) {
                    Icon(Icons.Sharp.Settings, "Settings")
                }
            }

            if(showSettings) {
                SettingsScreen(
                   // onFontFamilySelected = { fontFamily = it as GenericFontFamily },
                    onSaveChanges = {},
                    onDismiss = { showSettings = false },
                    fontFamily = fontFamily,
                    textAlign = textAlign,
                    textSize = fontSize
                )
            }
        }
    }
}