package org.soloqueue.app.journal
import java.io.File
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun JournalView() {
    //Create a File Object (Dummy Path)
    val file = File("/Users/zpham/IdeaProjects/OnlyGratitude/testText.txt");
    //If Path DNE, create the path all the way to directory
    if(!file.parentFile.exists()){
        file.parentFile.mkdirs()
    }

    var value by remember {
        mutableStateOf(
            TextFieldValue(
                //Check the file and read it
                text = file.readText(),
             )
        )
    }

    BasicTextField(
        value = value,
        onValueChange = {
            value = it
            file.writeText(it.text)
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.surface)
    )
}