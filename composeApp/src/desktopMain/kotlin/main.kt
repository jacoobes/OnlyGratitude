import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import services.FileService
import services.QuoteService
import services.StreakTrack


@OptIn(ExperimentalMaterial3Api::class)
fun main() = application {
    val fileService = FileService()
    val quoteService = QuoteService()
    val streakService = StreakTrack(fileService)
    Window(
        title = "OnlyGratitude",
        icon = painterResource("img.png"),
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val isDarkMode = remember { mutableStateOf(false) }
        val shouldViewJournals = remember { mutableStateOf(false) }
        val textContent = remember { mutableStateOf(EmptyJournalEntry) }
        val streakData = remember { mutableStateOf(streakService.getData()) }
        val emptyState = remember { mutableStateOf(isEmpty(textContent.value)) }
        val titleOfJournal = remember { mutableStateOf("") }
        val logo = painterResource("img.png")
        App(isDarkMode) {
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
                        title = titleOfJournal,
                        snackbarData = snackbarHostState,
                        textContent = textContent,
                        setEmpty = emptyState,
                        fileService = fileService,
                        steakService = streakService
                    )
                },
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(3.dp, MaterialTheme.colorScheme.inverseOnSurface)
                            )
                    ) {
                        if (!emptyState.value) {
                            GoBack(emptyState)
                        }
                        OpenMentalHealthResources()
                        LightMode(isDarkMode)
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
                                            quoteService.quoteGen(),
                                            spanStyle = SpanStyle(
                                                textDecoration = TextDecoration.Underline
                                            )
                                        ),
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                val streakCount = streakData.value[0]
                                //Maybe add something here to add the streak value
                                Text(
                                    AnnotatedString(
                                        "You've been on \uD83D\uDD25 " +
                                                "for $streakCount day${if(streakCount == 1) "" else "s"}!"
                                    ),
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
                    val pad = it.calculateTopPadding()
                    Column(
                        Modifier.padding(
                            top = pad,
                            start = pad,
                            end = pad
                        ).fillMaxWidth()
                    ) {
                        TextField(
                            value = titleOfJournal.value,
                            placeholder = { Text("Enter Title") },
                            onValueChange = { txtFieldValue ->
                                titleOfJournal.value = txtFieldValue
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(20.dp)
                        )
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.onSurface),
                            shadowElevation = 20.dp
                        ) {
                            JournalView(textContent)
                        }
                    }
                }
                if (shouldViewJournals.value) {
                    ShowAllJournalsWindow(isDarkMode, fileService, shouldViewJournals)
                }
            }
        }
    }
}