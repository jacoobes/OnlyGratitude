package org.soloqueue.app.journal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun JournalView(
) {
    var value by remember {
        mutableStateOf(
            TextFieldValue(
                text = "Hello",
             )
        )
    }
    BasicTextField(
        value = value,
        onValueChange = {
            value = it.copy(
                text =  it.text
            )
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.surface)
    )

}