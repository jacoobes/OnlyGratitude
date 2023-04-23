package services

class QuoteService {
    private val quotes = listOf(
        "Develop an attitude of gratitude. Say thank you to everyone you meet for everything they do for you. - Brian Tracy",
        "When gratitude becomes an essential foundation in our lives, miracles start to appear everywhere. - Emmanuel Dagher",
        "True forgiveness is when you can say, 'Thank you for that experience. - Oprah",
        "The Universe is not outside of you. Look inside yourself; everything that you want, you already are. - Rumi",
        "When you are grateful, fear disappears and abundance appears. - Tony Robbins",
        "What separates privilege from entitlement is gratitude. - Brené Brown",
        "Remember that sometimes not getting what you want is a wonderful stroke of luck. - Dalai Lama",
        "Don't let negativity from yesterday dull the sparkle of today. - Doreen White",
        "Instead of thinking 'I will be happy when,' try thinking 'I will be happy now. - Unknown"
    )
    fun quoteGen(): String{
        return quotes.random()
    }
}