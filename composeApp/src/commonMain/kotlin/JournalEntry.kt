data class JournalEntry(val date: String, val fileName: String, val content: String)

val EmptyJournalEntry = JournalEntry("000000", "!!!!!", "Open something :)")


fun isEmpty(j: JournalEntry): Boolean {
    return j.date == "000000" && j.fileName == "!!!!!" && j.content == "Open something :)"
}