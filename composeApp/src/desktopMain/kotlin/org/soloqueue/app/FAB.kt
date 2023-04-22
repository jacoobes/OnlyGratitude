package org.soloqueue.app

import EmptyJournalEntry
import JournalEntry
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import services.FileService

@Composable
fun AddIcon() {
    Icon(Icons.Sharp.Add, "Add")
}
@Composable
fun OpenFile(text: MutableState<String>, fileService: FileService) {
    FloatingActionButton(
        onClick = {
            val dialogResponse = fileService.fileOpen()
            if(dialogResponse == null) {
                text.value = fileService.fileOpen() ?: "Open Something :)"
            } else if(dialogResponse == "-1") {
                text.value = text.value
            } else {
                text.value = dialogResponse
            }
        },
        content = { AddIcon() }
    )
}