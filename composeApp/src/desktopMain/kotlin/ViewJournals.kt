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
    var selectedFile by remember { mutableStateOf<File?>(null) }
    Window(
        onCloseRequest = { shouldShow.value = !shouldShow.value },
        state = state,
        title = "OnlyGratitude - View Journals"
    ) {
        App(isDarkMode) {
            Surface(Modifier.fillMaxSize()) {
                val txtFiles = fileService.getTxtFiles()
                    .map { file ->
                        val pattern = "MMddyyyy"
                        val date = SimpleDateFormat(pattern).parse(file.nameWithoutExtension)
                        date to file
                    }
                Column (Modifier.fillMaxSize()) {
                    txtFiles.forEach { (date, file) ->
                        val formattedDate = SimpleDateFormat("MMM dd, yyyy").format(date)
                        val fileName = file.nameWithoutExtension.substringAfter("_")
                       Text(
                           text = "$formattedDate - $fileName",
                           modifier = Modifier.clickable {
                               selectedFile = if (selectedFile == file) null else file
                            }.padding(16.dp),
                           style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                        if (selectedFile == file) {
                            val fileContent = fileService.fileRead(file)
                            Text(
                                text = fileContent?: "",
                                modifier = Modifier.padding(start = 32.dp, end = 16.dp, bottom = 16.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}