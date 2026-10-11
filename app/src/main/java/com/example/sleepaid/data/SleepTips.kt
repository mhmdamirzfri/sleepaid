package com.example.sleepaid.data

import java.util.Calendar

object SleepTips {

    val tips = listOf(
        "Go to bed and wake up at the same time every day, even on weekends.",
        "Avoid caffeine for at least 6 hours before bed.",
        "Keep your bedroom cool, dark and quiet.",
        "Put your phone away 30–60 minutes before sleeping.",
        "Get some sunlight in the morning to set your body clock.",
        "Avoid heavy meals within 2–3 hours of bedtime.",
        "If you can't sleep after 20 minutes, get up and do something calm until you feel sleepy.",
        "Keep naps short (under 30 minutes) and before 3 PM.",
        "Exercise regularly, but not too close to bedtime.",
        "Use your bed only for sleep, not for work or scrolling.",
        "Try a breathing exercise to calm your mind before bed.",
        "Write tomorrow's to-do list before bed so your mind can rest.",
        "Drink enough water during the day, but less in the last hour before bed.",
        "A warm shower 1–2 hours before bed can help you fall asleep faster.",
        "Dim the lights in the evening to tell your body it's almost bedtime."
    )

    // randomize tips
    fun tipOfTheDay(): String {
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return tips[dayOfYear % tips.size]
    }
}