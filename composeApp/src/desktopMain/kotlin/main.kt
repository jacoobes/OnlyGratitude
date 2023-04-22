import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import services.*


@OptIn(ExperimentalMaterial3Api::class)

fun main() = application {
    val fileService = FileService()
    val quoteService = QuoteService()
    Window(
        title = "OnlyGratitude",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val inspirationalQuote = remember { mutableStateOf(quoteService.quoteGen()) }
        val textContent = remember { mutableStateOf("Open something :)") }
        App {
            Scaffold(
                topBar = {
                    WindowDraggableArea {
                        CenterAlignedTopAppBar(
                            title =  { Text("Welcome to OnlyGratitude") }
                        )
                    }
                },
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
                floatingActionButton =  {
                    OpenFile(textContent, fileService)
                },
                bottomBar = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        //settings here?

                    }
                }
            ) {
                Column (Modifier.padding(it.calculateTopPadding())) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        IconButton({ inspirationalQuote.value = quoteService.quoteGen() }) {
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