package services

class QuoteService {
    private val quotes = listOf(
        "The best way to predict the future is to invent it.",
        "You can't build a reputation on what you are going to do.",
        "Success is not final, failure is not fatal: It is the courage to continue that counts.",
        "Believe you can and you're halfway there.",
        "I have not failed. I've just found 10,000 ways that won't work.",
        "Don't watch the clock; do what it does. Keep going.",
        "Quality is not an act, it is a habit.",
        "The only way to do great work is to love what you do.",
        "You're Gonna Care What Other People Think And Be Someone You're Not Your Whole Life? You’re Fine As You Are. So, Talk In Your Own Words."
    )
    fun quoteGen(): String{
        return quotes.random()
    }
}