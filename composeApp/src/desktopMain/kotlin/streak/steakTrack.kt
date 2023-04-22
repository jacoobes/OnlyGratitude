package streak

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter



class steakTrack {

    /*
     * Call this method when one sign in is done
     */
    fun startSteakTrack(){

        val userFile = File("./data/user.og")

        val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        try{
            if(!userFile.exists()){
                userFile.createNewFile()
                userFile.writeText("0\n$currentDate\n-1")
            }
            var userSteak = userFile.readText().split("\n")[0].toInt()
            val userSigninDate = userFile.readText().split("\n")[1].toInt()
            val userEditDate = userFile.readText().split("\n")[2].toInt()

            if(userEditDate == -1) userSteak = 1
            else if(dateCompare(userEditDate) == 2) userSteak = 0

            userFile.writeText("$userSteak\n$userSigninDate\n$userEditDate")

        }catch(e: Exception){
            println("Error: ${e.message}")
        }
    }


    /*
     * Call this method when one edit is done
     */
    fun editSteakTrack(){

        val userFile = File("./data/user.og")

        val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        try{
            var userSteak = userFile.readText().split("\n")[0].toInt()
//            val userSigninDate = userFile.readText().split("\n")[1].toInt()
            val userEditDate = userFile.readText().split("\n")[2].toInt()

            if(userEditDate == -1) userSteak = 1
            else if(dateCompare(userEditDate) == 1) userSteak++
            userFile.writeText("$userSteak\n$currentDate\n$currentDate")

        }catch(e: Exception){
            println("Error: ${e.message}")
        }
    }


//    /* Compare the last edit date to the current date
//     * Return 0 if same day, 1 if last edit was yesterday, 2 if last edit was 2 or more days ago
//     */
private fun dateCompare(lastEditDate: Int): Int{
        val currentYear:Int = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")).toInt()
        val currentMonth:Int = LocalDate.now().format(DateTimeFormatter.ofPattern("MM")).toInt()
        val currentDay:Int = LocalDate.now().format(DateTimeFormatter.ofPattern("dd")).toInt()
        val lastEditYear:Int = lastEditDate/10000
        val lastEditMonth:Int = (lastEditDate - lastEditYear*10000)/100
        val lastEditDay:Int = lastEditDate - lastEditYear*10000 - lastEditMonth*100



        if(currentYear > lastEditYear){
            if(currentMonth == 1 && currentDay == 1 && lastEditMonth == 12 && lastEditDay == 31)
                return 0
            return 2
        }
        if(currentMonth > lastEditMonth){
            if(currentDay == 1 && lastEditDay == lastDay(lastEditMonth, lastEditYear))
                return 0
            return 2
        }
        if(currentDay > (lastEditDay + 1)){
            return 2
        }
        return 1
    }

    private fun isLeapYear(year: Int): Boolean{
        if(year % 4 == 0){
            if(year % 100 == 0){
                return year % 400 == 0
            }
            return true
        }
        return false
    }
    private fun lastDay(month: Int, year: Int): Int{
        if(month == 2){
            return if(isLeapYear(year)) 29 else 28
        }
        if(month == 4 || month == 6 || month == 9 || month == 11){
            return 30
        }
        return 31
    }
}