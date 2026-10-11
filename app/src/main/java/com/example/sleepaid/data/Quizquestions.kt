package com.example.sleepaid.data

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val answerIndex: Int,        // counts from 0, so 2 = the third option
    val explanation: String
)

val quizQuestions = listOf(
    QuizQuestion(
        "How long does one sleep cycle last on average?",
        listOf("30 minutes", "60 minutes", "90 minutes", "3 hours"),
        2,
        "A sleep cycle takes about 90 minutes, and it repeats through the night."
    ),
    QuizQuestion(
        "How much sleep should adults get each night?",
        listOf("4 or more hours", "5 or more hours", "7 or more hours", "12 or more hours"),
        2,
        "Sleep experts recommend that adults regularly sleep 7 or more hours a night."
    ),
    QuizQuestion(
        "Which stage is deep sleep?",
        listOf("N1", "N2", "N3", "REM"),
        2,
        "N3 is deep sleep. Your body repairs itself and you are hardest to wake."
    ),
    QuizQuestion(
        "In which stage do most vivid dreams happen?",
        listOf("N1", "N3", "REM", "N2"),
        2,
        "REM is dreaming sleep. Your brain is very active, but your muscles relax."
    ),
    QuizQuestion(
        "How many sleep cycles do most people have in a night?",
        listOf("1 to 2", "4 to 6", "10 to 12", "About 20"),
        1,
        "Most people go through 4 to 6 cycles in a night."
    ),
    QuizQuestion(
        "Waking up from which stage usually makes you feel the groggiest?",
        listOf("N1", "N2", "Deep sleep (N3)", "REM"),
        2,
        "Waking from deep sleep causes the most sleep inertia, that heavy, groggy feeling."
    ),
    QuizQuestion(
        "In a 2023 review, about how much did caffeine cut total sleep?",
        listOf("5 minutes", "45 minutes", "2 hours", "It had no effect"),
        1,
        "Caffeine cut total sleep by about 45 minutes and reduced deep sleep."
    ),
    QuizQuestion(
        "How long before bed should you have your last regular coffee?",
        listOf("30 minutes", "2 hours", "About 9 hours", "It doesn't matter"),
        2,
        "Researchers suggest at least 8.8 hours, so about 2 PM for an 11 PM bedtime."
    ),
    QuizQuestion(
        "Each extra hour of screen use in bed was linked to...",
        listOf(
            "Lower odds of insomnia",
            "59% higher odds of insomnia",
            "Deeper sleep",
            "No change at all"
        ),
        1,
        "A study of over 45,000 students found 59% higher odds of insomnia and about 24 minutes less sleep."
    ),
    QuizQuestion(
        "Which of these is good sleep hygiene?",
        listOf(
            "A different bedtime every night",
            "A cool, dark and quiet bedroom",
            "Scrolling on your phone in bed",
            "Coffee in the evening"
        ),
        1,
        "A cool, dark and quiet bedroom helps your body settle into sleep."
    ),
    QuizQuestion(
        "Insomnia is called chronic when it happens 3 nights a week for at least...",
        listOf("1 week", "2 weeks", "3 months", "5 years"),
        2,
        "If it lasts 3 months or more, it's time to see a doctor. It can be treated."
    ),
    QuizQuestion(
        "Which of these can be a sign of sleep apnoea?",
        listOf(
            "Breathing that stops and starts during sleep",
            "Having dreams",
            "Waking at the same time each day",
            "Feeling refreshed in the morning"
        ),
        0,
        "Pauses in breathing, loud snoring, gasping and daytime tiredness are warning signs."
    ),
    QuizQuestion(
        "What mainly sets your body clock?",
        listOf("Daylight", "Music", "The food you eat", "Your phone's alarm"),
        0,
        "Daylight is the main signal that keeps your 24-hour body clock on time."
    ),
    QuizQuestion(
        "Regularly sleeping too little is linked to a higher risk of...",
        listOf("High blood pressure", "Better memory", "A stronger immune system", "Growing taller"),
        0,
        "A 2025 review linked short sleep to higher blood pressure, obesity and diabetes risk."
    ),
    QuizQuestion(
        "Besides the number of hours, what else does healthy sleep need?",
        listOf(
            "Good quality and a regular schedule",
            "Sleeping in every weekend",
            "A very loud alarm",
            "Sleeping only when tired"
        ),
        0,
        "Healthy sleep needs good quality, sensible timing and regularity, not just hours."
    )
)