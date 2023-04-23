import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import org.soloqueue.app.App
import services.FileService
import java.io.File
import java.text.SimpleDateFormat

@Composable
fun ShowAllJournalsWindow(
    isDarkMode: MutableState<Boolean>,
    fileService: FileService,
    shouldShow: MutableState<Boolean>
) {
    val state = rememberWindowState()
    Window(
        onCloseRequest = { shouldShow.value = !shouldShow.value },
        state = state
    ) {
        App(isDarkMode) {
            Surface(Modifier.fillMaxSize()) {
                Text("Show journals")

            }
        }
    }

}