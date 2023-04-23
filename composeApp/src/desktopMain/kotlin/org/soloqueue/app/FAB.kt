package org.soloqueue.app

import EmptyJournalEntry
import JournalEntry
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import services.FileService
import services.StreakTrack
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFile(
    title: MutableState<String>,
    snackbarData: SnackbarHostState,
    textContent: MutableState<JournalEntry>,
    setEmpty: MutableState<Boolean>,
    fileService: FileService,
    steakService: StreakTrack,
) {
    val scope = rememberCoroutineScope()
    FloatingActionButton(
        onClick = {
            //When FAB IS + ICON
            if(setEmpty.value) {
                scope.launch {
                    snackbarData.showSnackbar(
                        message = "Great! Click again to save. Remember, it's permanent once you're done!",
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
                }
            } else {
                val date = LocalDate.now()
                val dateString = date.format(DateTimeFormatter.ofPattern("MMddyyyy"))
                scope.launch {
                    fileService.newJournal(dateString, title.value, textContent.value.content)
                    textContent.value = EmptyJournalEntry
                    setEmpty.value = true
                    title.value = ""
                    steakService.editSteakTrack()
                }
            }
            setEmpty.value = false
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