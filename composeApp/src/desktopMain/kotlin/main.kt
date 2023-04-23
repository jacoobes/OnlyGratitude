import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.painterResource
import org.soloqueue.app.*
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
        val shouldViewJournals = remember { mutableStateOf(false) }
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
                            title = {
                                Text(
                                    "OnlyGratitude",
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                            },
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
                floatingActionButton = {
                    AddFile(
                        titleOfJournal.value,
                        emptyState,
                        fileService
                    )
                },
                bottomBar = {
                    Column(Modifier.fillMaxWidth()) {
                        Divider()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(1.dp, MaterialTheme.colorScheme.inverseOnSurface)
                                )
                        ) {
                            Save(fileService, EmptyJournalEntry)
                        }
                    }
                }
            ) {
                if (emptyState.value) {
                    Column(
                        Modifier
                            .padding(it.calculateTopPadding())
                            .fillMaxWidth()
                    ) {
                        Row {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(75.dp),
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Row(Modifier.padding(vertical = 10.dp)) {
                                    Text(
                                        AnnotatedString(
                                            inspirationalQuote.value,
                                            spanStyle = SpanStyle(
                                                textDecoration = TextDecoration.Underline
                                            )
                                        ),
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                //Maybe add something here to add the streak value
                                Text(
                                    AnnotatedString("You've been on \uD83D\uDD25 for x days!"),
                                    fontWeight = FontWeight.Bold
                                )
                                Button(
                                    { shouldViewJournals.value = !shouldViewJournals.value },
                                ) {
                                    Text("View Journals")
                                }
                            }
                        }
                    }
                } else {
                    Column(
                        Modifier.padding(it.calculateTopPadding())
                    ) {
                        OutlinedTextField(
                            value = titleOfJournal.value,
                            placeholder = { Text("Enter Title") },
                            onValueChange = { txtFieldValue ->
                                titleOfJournal.value = txtFieldValue
                            },
                        )
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.onSurface),
                            shadowElevation = 10.dp
                        ) {
                            JournalView(textContent)
                        }
                    }
                }
                if (shouldViewJournals.value) {
                    ShowAllJournalsWindow(fileService, shouldViewJournals)
                }
            }
        }
    }
}