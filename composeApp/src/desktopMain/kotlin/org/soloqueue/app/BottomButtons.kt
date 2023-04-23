package org.soloqueue.app

import JournalEntry
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.List
import androidx.compose.runtime.Composable
import services.FileService


@Composable
fun Save(
    fileService: FileService,
    journalEntry: JournalEntry
) {
    IconButton(
        onClick = {
            println("save")
            fileService.fileSave("")
        }
    ) {
        Icon(
            Icons.Sharp.ExitToApp, contentDescription = "Open resources")
    }
}

fun Open() {

}
