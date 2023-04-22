package services
import java.io.File
import java.nio.file.Paths
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class FileService {
    private var file: File? = null
    private val workingDirectoryPath = Paths.get(System.getProperty("user.dir"), "../og")
    val otherPath = Paths.get("$workingDirectoryPath/other")
    val dataPath = Paths.get("$workingDirectoryPath/data")
    val journalsPath = Paths.get(dataPath.toString(), "journals")
    fun fileOpen(): String? {
        println(workingDirectoryPath)
        val fileChooser = JFileChooser(journalsPath.toString())
        val filter = FileNameExtensionFilter("Journal Files (*.jnl)", "jnl")
        fileChooser.fileFilter = filter
        val returnVal = fileChooser.showOpenDialog(null)
        return if (returnVal == JFileChooser.APPROVE_OPTION){
            file = fileChooser.selectedFile
            fileRead(file)
        } else {
            null
        }
    }

    private fun fileRead(file: File?): String? {
        return file?.readText()
    }

    fun fileSave(text: String) {
        if(file == null){
            val fileChooser = JFileChooser()
            val filter = FileNameExtensionFilter("Journal Files (*.jnl)", ".jnl")
            fileChooser.fileFilter = filter
            val returnVal = fileChooser.showSaveDialog(null)
            if (returnVal == JFileChooser.APPROVE_OPTION){
                file = fileChooser.selectedFile
            }
        }
        file?.writeText(text)
    }
}