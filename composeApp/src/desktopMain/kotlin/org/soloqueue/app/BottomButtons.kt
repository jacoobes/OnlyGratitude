package org.soloqueue.app

import JournalEntry
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
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
        }
    ) {
        Icon(Icons.Sharp.KeyboardArrowDown, contentDescription = null)
    }
}

fun Open() {

}
