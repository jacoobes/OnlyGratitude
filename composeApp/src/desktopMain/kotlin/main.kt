import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.soloqueue.app.App
import org.soloqueue.app.JournalView
import org.soloqueue.app.OpenFile
import org.soloqueue.app.Save
import services.*


@OptIn(ExperimentalMaterial3Api::class)

fun main() = application {
    val fileService = FileService()
    val quoteService = QuoteService()
    val streakService = StreakTrack(fileService)
    Window(
        title = "OnlyGratitude",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val inspirationalQuote = remember { mutableStateOf(quoteService.quoteGen()) }
        val textContent = remember { mutableStateOf(EmptyJournalEntry) }
        val emptyState = remember { mutableStateOf(isEmpty(textContent.value)) }
        val titleOfJournal = remember { mutableStateOf("") }
        val logo = painterResource("img.png")
        App {
            Scaffold(
                topBar = {
                    WindowDraggableArea {
                        CenterAlignedTopAppBar(
                            title =  { Text(
                                "OnlyGratitude",
                                color = MaterialTheme.colorScheme.onSecondary
                            ) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                            ),
                            navigationIcon = {
                                Icon(
                                    logo,
                                    contentDescription = null,
                                    modifier = Modifier.size(width = 50.dp, height = 50.dp)
                                )
                            }
                        )
                    }
                },
                snackbarHost = { SnackbarHost(snackbarHostState) },
                floatingActionButton =  {
                    OpenFile(textContent, fileService)
                },
                bottomBar = {
                    Column(Modifier.fillMaxWidth()) {
                        Divider()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(1.dp, MaterialTheme.colorScheme.inverseOnSurface)
                                )) {
                            Save(fileService, EmptyJournalEntry)
                        }
                    }
                }
            ) {
                Column (
                    Modifier
                        .padding(it.calculateTopPadding())
                        .fillMaxWidth()
                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.background(MaterialTheme.colorScheme.inversePrimary).fillMaxWidth(),
                    ){
                        IconButton(
                            { inspirationalQuote.value = quoteService.quoteGen() },
                        ) {
                            Icon(Icons.Sharp.Refresh, "refresh")
                        }
                        Text(
                            inspirationalQuote.value,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Surface(
                        Modifier
                            .fillMaxSize(),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        JournalView(textContent)
                    }
                }
            }
        }
    }
}