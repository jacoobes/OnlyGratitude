package services

import java.io.File
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class StreakTrack(private val fileService: FileService) {
    private var data: List<Int>? = null
    init {
        data = getData()
        startStreakTrack()
    }
    /*
     * Call this method when one sign in is done
     */
    fun startStreakTrack():Int {
        val userConfigPath = Paths.get(fileService.dataPath.toString(), "user.og")
        val userFile = File(userConfigPath.toString())
        var userStreak:Int = data!!.elementAt(0)
        val userSigninDate:Int = data!!.elementAt(1)
        val userEditDate:Int = data!!.elementAt(2)

        println("userSigninDate: $userSigninDate")
        println("userEditDate: $userEditDate")

        val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMDDyyyy"))
//        try {
            if (!userFile.exists()) {
                userFile.createNewFile()
                userFile.writeText("0\n$currentDate\n-1")
            }
//            userStreak = userFile.readText().split("\n")[0].toInt()
//            val userSigninDate = userFile.readText().split("\n")[1].toInt()
//            val userEditDate = userFile.readText().split("\n")[2].toInt()

            if (userEditDate == -1) userStreak = 1
            else if (dateCompare(userEditDate) == 2) userStreak = 0

            val toString: String = String.format("%d\n%08d\n%08d", userStreak, userSigninDate, userEditDate)
            userFile.writeText(toString)

//        } catch (e: Exception) {
//            println("Error: ${e.message}")
//        }
        return userStreak
    }


    /*
     * Call this method when one edit is done
     */
    fun editSteakTrack() {

        val userFile = File(Paths.get(fileService.dataPath.toString(), "user.og").toString())

        val currentDate:Int = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy")).toInt()
//        println("Current date: $currentDate")
        var userStreak = data!!.elementAt(0)
//        val userSigninDate = data!!.elementAt(1)
        val userEditDate = data!!.elementAt(2)

//        try {
//            var userStreak = userFile.readText().split("\n")[0].toInt()
//            val userSigninDate = userFile.readText().split("\n")[1].toInt()
//            val userEditDate = userFile.readText().split("\n")[2].toInt()

            if (userEditDate == -1) userStreak = 1
            else if (dateCompare(userEditDate) == 1) userStreak++
            val toString: String = String.format("%d\n%08d\n%08d", userStreak, currentDate, currentDate)
            userFile.writeText(toString)
//            userFile.writeText("$userStreak\n$currentDate\n$currentDate")

//        } catch (e: Exception) {
//            println("Error: ${e.message}")
//        }
    }

    /**
     *  Fetches data from file.
     *  Tries to read the file once to prevent slowdowns
     */
    fun getData() : List<Int> {
        return if(data == null) {
            val userFile = File(Paths.get(fileService.dataPath.toString(), "user.og").toString())
            val parsedData = userFile.readText().split("\n").map { it.toInt() }
            parsedData
        } else {
            data!!
        }
    }


    /* Compare the last edit date to the current date
     * Return 0 if same day, 1 if last edit was yesterday, 2 if last edit was 2 or more days ago
     */
    private fun dateCompare(lastEditDate: Int): Int {
        val nowDate = LocalDate.now()
        val currentYear: Int = nowDate.format(DateTimeFormatter.ofPattern("yyyy")).toInt()
        val currentMonth: Int = nowDate.format(DateTimeFormatter.ofPattern("MM")).toInt()
        val currentDay: Int = nowDate.format(DateTimeFormatter.ofPattern("dd")).toInt()
        val lastEditYear: Int = lastEditDate % 10000;
        val lastEditMonth: Int = lastEditDate / 1000000
        val lastEditDay: Int = lastEditDate / 10000 % 100

        println("Current date: $currentYear-$currentMonth-$currentDay")
        println("Last edit date: $lastEditYear-$lastEditMonth-$lastEditDay")

        if (currentYear > lastEditYear) {
            if (currentMonth == 1 && currentDay == 1 && lastEditMonth == 12 && lastEditDay == 31)
                return 1
        }
        if (currentMonth > lastEditMonth) {
            if (currentDay == 1 && lastEditDay == lastDay(lastEditMonth, lastEditYear))
                return 1
        }
        if (currentDay == (lastEditDay + 1)) {
            return 1
        } else if(currentDay == lastEditDate) {
            return 0
        }
        return 2

    }

    private fun isLeapYear(year: Int): Boolean {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0
            }
            return true
        }
        return false
    }

    private fun lastDay(month: Int, year: Int): Int {
        if (month == 2) {
            return if (isLeapYear(year)) 29 else 28
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30
        }
        return 31
    }
}