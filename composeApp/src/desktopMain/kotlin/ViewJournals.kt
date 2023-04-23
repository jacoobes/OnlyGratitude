import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.window.singleWindowApplication

import org.soloqueue.app.App
import services.FileService

@Composable
fun ShowAllJournalsWindow(
    fileService: FileService,
    shouldShow: MutableState<Boolean>
) {
    val state = rememberWindowState()
    Window(
        onCloseRequest = { shouldShow.value = !shouldShow.value },
        state = state
    ) {
        App {
            Surface(Modifier.fillMaxSize()) {
                Text("Show journals")

            }
        }
    }

}