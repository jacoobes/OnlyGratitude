package org.soloqueue.app
import JournalEntry
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.SystemFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JournalView(textContent: MutableState<JournalEntry>) {
    var fontSize = remember { mutableStateOf(16.sp) }
    var fontFamily = remember { mutableStateOf<SystemFontFamily>(FontFamily.Cursive) }
    var textAlign = remember { mutableStateOf(TextAlign.Start) }
    var showSettings by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = textContent.value.content,
        onValueChange = {
            textContent.value = JournalEntry(
                textContent.value.date,
                textContent.value.fileName,
                it.replace("\t", "     ") //fix compose tab issue
            )
        },
        modifier = Modifier.padding(10.dp),
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

                IconButton(
                    onClick = { showSettings = true },
                ) {
                    Icon(Icons.Sharp.Settings, "Settings")
                }
            }
            if(showSettings) {
                SettingsScreen(
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