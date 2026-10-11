package com.example.sleepaid.data

// One part of an article: optional heading, a paragraph, and optional bullet points
data class ArticleSection(
    val heading: String?,                       // ? = optional
    val body: String,
    val bullets: List<String> = emptyList()
)

data class Article(
    val id: Int,
    val title: String,
    val summary: String,
    val readMinutes: Int,
    val sections: List<ArticleSection>,
    val sources: List<String>
)

// ---------- Sources (written once, reused) ----------
private const val SRC_STATPEARLS =
    "Patel AK, Reddy V, Shumway KR, Araujo JF. Physiology, Sleep Stages. StatPearls, NCBI Bookshelf. Updated 2024."
private const val SRC_ISHII =
    "Ishii M, Taweesedt PT, Chick CF, O'Hara R, Kawai M. From macro to micro: slow-wave sleep and its pivotal health implications. Frontiers in Sleep. 2024."
private const val SRC_RUBY =
    "Ruby P, Evangelista E, Bastuji H, Peter-Derex L. From physiological awakening to pathological sleep inertia. Neurophysiologie Clinique. 2024."
private const val SRC_AASM =
    "Ramar K, Malhotra RK, Carden KA, et al. Sleep is essential to health: an American Academy of Sleep Medicine position statement. J Clin Sleep Med. 2021."
private const val SRC_SHAH =
    "Shah AS, Pant MR, Bommasamudram T, et al. Effects of Sleep Deprivation on Physical and Mental Health Outcomes: An Umbrella Review. American Journal of Lifestyle Medicine. 2025."
private const val SRC_BARANWAL =
    "Baranwal N, Yu PK, Siegel NS. Sleep physiology, pathophysiology, and sleep hygiene. Progress in Cardiovascular Diseases. 2023."
private const val SRC_GARDINER =
    "Gardiner C, Weakley J, Burke LM, et al. The effect of caffeine on subsequent sleep: a systematic review and meta-analysis. Sleep Medicine Reviews. 2023."
private const val SRC_HJETLAND =
    "Hjetland GJ, et al. How and when screens are used: comparing different screen activities and sleep in Norwegian university students. Frontiers in Psychiatry. 2025."
private const val SRC_AASM_INSOMNIA =
    "American Academy of Sleep Medicine. Insomnia. sleepeducation.org. Reviewed 2026."
private const val SRC_NHS_APNOEA =
    "NHS. Sleep apnoea. nhs.uk."

// ---------- The 8 articles ----------
val articles = listOf(

    Article(
        id = 0,
        title = "What is sleep?",
        summary = "What happens in your brain and body at night.",
        readMinutes = 2,
        sections = listOf(
            ArticleSection(
                heading = null,
                body = "Sleep is not just switching off. While you sleep, your brain stays busy " +
                        "sorting memories and resetting for the next day, and your body repairs itself " +
                        "and saves energy."
            ),
            ArticleSection(
                heading = "Two systems decide when you sleep",
                body = "Feeling sleepy comes from two things working together:",
                bullets = listOf(
                    "Sleep pressure: the longer you stay awake, the more your need for sleep builds up.",
                    "Your body clock: a roughly 24-hour rhythm, set mostly by daylight, that makes you alert in the day and sleepy at night."
                )
            ),
            ArticleSection(
                heading = "A biological need",
                body = "The American Academy of Sleep Medicine describes sleep as a biological " +
                        "necessity. It supports learning, mood, the immune system and heart health."
            )
        ),
        sources = listOf(SRC_STATPEARLS, SRC_RUBY, SRC_AASM)
    ),

    Article(
        id = 1,
        title = "The stages of sleep",
        summary = "Light sleep, deep sleep and dreaming sleep.",
        readMinutes = 2,
        sections = listOf(
            ArticleSection(
                heading = null,
                body = "Each night you go through repeating sleep cycles. A cycle takes about " +
                        "90 minutes, and most people have 4 to 6 cycles a night."
            ),
            ArticleSection(
                heading = "The four stages",
                body = "Each cycle moves through these stages:",
                bullets = listOf(
                    "N1: very light sleep as you drift off. It lasts only a few minutes.",
                    "N2: light sleep. Your heart rate slows and your body temperature drops.",
                    "N3: deep sleep. Your body repairs itself, and you are hardest to wake.",
                    "REM: dreaming sleep. Your brain is very active, but your muscles relax."
                )
            ),
            ArticleSection(
                heading = "How the night changes",
                body = "Early in the night you get more deep sleep. Towards morning, REM " +
                        "periods get longer. Waking up from deep sleep tends to leave you feeling " +
                        "the groggiest, which is called sleep inertia."
            )
        ),
        sources = listOf(SRC_STATPEARLS, SRC_ISHII)
    ),

    Article(
        id = 2,
        title = "Why sleep matters",
        summary = "How sleep affects your brain, mood and body.",
        readMinutes = 2,
        sections = listOf(
            ArticleSection(
                heading = null,
                body = "Good sleep affects almost every part of your health:",
                bullets = listOf(
                    "Brain: sleep helps you learn, remember and concentrate.",
                    "Mood: poor sleep is linked to more anxiety, irritability and low mood.",
                    "Body: sleep supports your immune system, heart and metabolism.",
                    "Safety: sleepiness slows reactions and raises the risk of accidents."
                )
            ),
            ArticleSection(
                heading = "Quality and timing count too",
                body = "Healthy sleep is not only about hours. It also needs good quality, " +
                        "sensible timing and a regular schedule."
            )
        ),
        sources = listOf(SRC_AASM, SRC_SHAH)
    ),

    Article(
        id = 3,
        title = "Effects of sleep deprivation",
        summary = "What happens when you don't get enough sleep.",
        readMinutes = 3,
        sections = listOf(
            ArticleSection(
                heading = "In the short term",
                body = "Even a few bad nights can cause:",
                bullets = listOf(
                    "Trouble concentrating and remembering",
                    "Feeling irritable, anxious or low",
                    "Slower reactions and more mistakes"
                )
            ),
            ArticleSection(
                heading = "Over months and years",
                body = "A 2025 review of 29 research reviews found that regularly sleeping " +
                        "too little is linked to:",
                bullets = listOf(
                    "Higher blood pressure (1.2 to 1.6 times the risk)",
                    "Obesity (about 1.5 times as likely)",
                    "A higher risk of type 2 diabetes and stroke",
                    "More anxiety and depression"
                )
            ),
            ArticleSection(
                heading = "The good news",
                body = "Many of these risks are linked to sleep you can improve. The same " +
                        "review recommends 7 to 9 hours a night for adults."
            )
        ),
        sources = listOf(SRC_SHAH)
    ),

    Article(
        id = 4,
        title = "How much sleep do you need?",
        summary = "The recommended hours, and signs you need more.",
        readMinutes = 2,
        sections = listOf(
            ArticleSection(
                heading = null,
                body = "The American Academy of Sleep Medicine and the Sleep Research Society " +
                        "recommend that adults sleep 7 or more hours a night on a regular basis."
            ),
            ArticleSection(
                heading = "Signs you may need more sleep",
                body = "",
                bullets = listOf(
                    "You need an alarm to wake up and struggle to get out of bed",
                    "You feel sleepy during classes, meetings or driving",
                    "You sleep much longer on weekends to catch up"
                )
            ),
            ArticleSection(
                heading = "Regularity matters",
                body = "Going to bed and waking up at similar times every day, even on " +
                        "weekends, helps keep your body clock steady."
            )
        ),
        sources = listOf(SRC_AASM, SRC_SHAH)
    ),

    Article(
        id = 5,
        title = "Sleep hygiene tips",
        summary = "Everyday habits that help you sleep better.",
        readMinutes = 2,
        sections = listOf(
            ArticleSection(
                heading = null,
                body = "Sleep hygiene means the habits and surroundings that help you sleep well:",
                bullets = listOf(
                    "Keep a regular sleep and wake time.",
                    "Get daylight in the morning, and keep lights dim in the evening.",
                    "Exercise regularly, but not right before bed.",
                    "Limit caffeine and alcohol, especially later in the day.",
                    "Keep your bedroom cool, dark and quiet.",
                    "Use a relaxation routine, like breathing or meditation, to wind down."
                )
            ),
            ArticleSection(
                heading = "Give it time",
                body = "Habits take a few weeks to make a difference. Try changing one or two " +
                        "things at a time."
            )
        ),
        sources = listOf(SRC_BARANWAL)
    ),

    Article(
        id = 6,
        title = "Screens, caffeine and sleep",
        summary = "How your phone and your coffee affect your night.",
        readMinutes = 3,
        sections = listOf(
            ArticleSection(
                heading = "Caffeine",
                body = "A 2023 review of studies found that caffeine:",
                bullets = listOf(
                    "Cut total sleep by about 45 minutes",
                    "Added about 9 minutes to the time it takes to fall asleep",
                    "Reduced deep sleep"
                )
            ),
            ArticleSection(
                heading = null,
                body = "To protect your sleep, the researchers suggest having your last regular " +
                        "coffee at least 8.8 hours before bed. For a 11 PM bedtime, that's around 2 PM."
            ),
            ArticleSection(
                heading = "Screens in bed",
                body = "A 2025 study of over 45,000 university students found that each extra " +
                        "hour of screen use after going to bed was linked to:",
                bullets = listOf(
                    "59% higher odds of insomnia symptoms",
                    "About 24 minutes less sleep"
                )
            ),
            ArticleSection(
                heading = null,
                body = "Social media was no worse than films or games. The main problem was the " +
                        "time screens take away from sleep. Try stopping screens 30 to 60 minutes " +
                        "before bed, and turn off notifications at night."
            )
        ),
        sources = listOf(SRC_GARDINER, SRC_HJETLAND)
    ),

    Article(
        id = 7,
        title = "When to see a doctor",
        summary = "Signs that a sleep problem needs medical help.",
        readMinutes = 2,
        sections = listOf(
            ArticleSection(
                heading = "Insomnia",
                body = "Trouble falling or staying asleep at least 3 nights a week for 3 months " +
                        "or more is called chronic insomnia. If it lasts that long, see a doctor. " +
                        "It can be treated."
            ),
            ArticleSection(
                heading = "Possible sleep apnoea",
                body = "See a doctor if you or someone who sleeps near you notices:",
                bullets = listOf(
                    "Loud snoring",
                    "Breathing that stops and starts during sleep",
                    "Gasping or choking sounds at night",
                    "Feeling very tired during the day, or morning headaches"
                )
            ),
            ArticleSection(
                heading = "Remember",
                body = "This app gives general information and is not medical advice. If you " +
                        "are worried about your sleep or your health, talk to a doctor."
            )
        ),
        sources = listOf(SRC_AASM_INSOMNIA, SRC_NHS_APNOEA)
    )
)