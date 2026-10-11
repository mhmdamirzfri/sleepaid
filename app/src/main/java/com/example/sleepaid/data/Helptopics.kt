package com.example.sleepaid.data

data class HelpTopic(
    val title: String,
    val steps: List<String>
)

val helpTopics = listOf(
    HelpTopic(
        "Sleep Cycle Calculator",
        listOf(
            "Go to Sleep Tools, then Sleep Cycle Calculator.",
            "Choose \"Wake up at\" or \"Go to bed at\".",
            "Tap the time to change it, or use the current time.",
            "Pick a time from the list. \"Recommended\" times give 5–6 sleep cycles."
        )
    ),
    HelpTopic(
        "Water Intake Calculator",
        listOf(
            "Go to Sleep Tools, then Water Intake Calculator.",
            "Enter your weight in kg.",
            "Choose your activity level and switch on hot weather if needed.",
            "Tap Calculate to see your daily amount in litres and glasses."
        )
    ),
    HelpTopic(
        "Breathing Exercises",
        listOf(
            "Go to Relaxation Tools, then Breathing Exercises.",
            "Choose 4-7-8, Box or Relax.",
            "Press Start and breathe in time with the circle.",
            "Press Stop at any time. Stop if you feel dizzy."
        )
    ),
    HelpTopic(
        "Guided Meditation",
        listOf(
            "Go to Relaxation Tools, then Guided Meditation.",
            "Choose a session.",
            "Read each step, then tap Next when you are ready. Tap Back to go back."
        )
    ),
    HelpTopic(
        "Relaxing Sounds",
        listOf(
            "Go to Relaxation Tools, then Relaxing Sounds.",
            "Tap play on a sound. It repeats until you press stop.",
            "Use the slider to change the volume.",
            "The sound stops when you leave the screen."
        )
    ),
    HelpTopic(
        "Sleep Information",
        listOf(
            "Tap Sleep Information on the Home screen.",
            "Tap an article to read it. Sources are listed at the bottom."
        )
    ),
    HelpTopic(
        "Quiz",
        listOf(
            "Tap Quiz on the Home screen.",
            "Answer 10 questions. The correct answer is shown after each one.",
            "See your score at the end and tap Try again for new questions."
        )
    )
)