package org.soloqueue.app

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import services.FileService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddIcon() {
    Icon(Icons.Sharp.Add, "Add")
}
@Composable
fun SaveIcon() {
    Icon(Icons.Sharp.Check, "Save")
}
@Composable
fun AddFile(
    title: String,
    setEmpty: MutableState<Boolean>,
    fileService: FileService
) {
    FloatingActionButton(
        onClick = {
            val date = LocalDate.now()
            val dateString = date.format(DateTimeFormatter.ofPattern("MMddyyyy"))
            //fileService.newJournal(dateString, title , "Enter Something :)")
            if(setEmpty.value) {
                //When FAB IS + ICON
            } else {
                //When FAB is Check
            }
            setEmpty.value = !setEmpty.value
        },
        content = {
            if(setEmpty.value) {
                AddIcon()
            } else {
                SaveIcon()
            }
        }
    )
}