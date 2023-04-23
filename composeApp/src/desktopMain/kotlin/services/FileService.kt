package services
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class FileService {
    private var file: File? = null
    private val workingDirectoryPath = Paths.get(System.getProperty("user.dir"), "../og")
    val otherPath = Paths.get("$workingDirectoryPath/other")
    val dataPath = Paths.get("$workingDirectoryPath/data")
    val journalsPath = Paths.get(dataPath.toString(), "journals")



    /*
     * This method creates a new journal file as txt
     * @param date: String, the current date. format: yyyyMMdd
     * so the file name could be sorted by date
     * @param title: String
     * @param text: String
     * Filename: date_title.txt
     */
    fun newJournal(date: String, title: String, text: String) {
        val file = File(journalsPath.toString(), date + "_" + "$title.jnl")
        if(file.exists()) {
            throw Exception("File already exists")
        }else {
            file.createNewFile()
        }
        file.writeText(text)
    }


    fun fileRead(file: File?): String? {
        return file?.readText()
    }

    fun getTxtFiles(): List<File> {
        val txtFiles = mutableListOf<File>()
        if (Files.isDirectory(journalsPath)) {
            Files.list(journalsPath).forEach { filePath ->
                val file = filePath.toFile()
                if (file.isFile && file.extension.equals("jnl", ignoreCase = true)) {
                    txtFiles.add(file)
                }
            }
        }
        return txtFiles
    }

}