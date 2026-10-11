package com.example.sleepaid.data

import com.example.sleepaid.R

// breathe
data class BreathPhase(
    val label: String,
    val seconds: Int,
    val targetSize: Float
)

data class BreathPattern(
    val name: String,
    val description: String,
    val phases: List<BreathPhase>
)

private const val BIG = 1.0f
private const val SMALL = 0.55f

val breathingPatterns = listOf(
    BreathPattern(
        name = "4-7-8",
        description = "Breathe in for 4, hold for 7, then breathe out slowly for 8. " +
                "The long out-breath helps your body slow down. Try 4 rounds.",
        phases = listOf(
            BreathPhase("Breathe in", 4, BIG),
            BreathPhase("Hold", 7, BIG),
            BreathPhase("Breathe out", 8, SMALL)
        )
    ),
    BreathPattern(
        name = "Box",
        description = "Four equal steps of 4 seconds: in, hold, out, hold. " +
                "Simple and steady, good for calming a busy mind.",
        phases = listOf(
            BreathPhase("Breathe in", 4, BIG),
            BreathPhase("Hold", 4, BIG),
            BreathPhase("Breathe out", 4, SMALL),
            BreathPhase("Hold", 4, SMALL)
        )
    ),
    BreathPattern(
        name = "Relax",
        description = "In for 4, out for 6. That's 6 breaths a minute, " +
                "a slow, gentle pace with no holding.",
        phases = listOf(
            BreathPhase("Breathe in", 4, BIG),
            BreathPhase("Breathe out", 6, SMALL)
        )
    )
)

//meditate
data class MeditationStep(
    val text: String,
    val seconds: Int
)

data class MeditationSession(
    val title: String,
    val shortName: String,
    val intro: String,
    val steps: List<MeditationStep>
) {
    // Total length rounded up to whole minutes
    val minutes: Int get() = (steps.sumOf { it.seconds } + 59) / 60
}

val meditationSessions = listOf(
    MeditationSession(
        title = "Body Scan",
        shortName = "Body Scan",
        intro = "Relax each part of your body, from your feet to your head.",
        steps = listOf(
            MeditationStep("Lie down comfortably and close your eyes. Let your arms rest by your sides.", 20),
            MeditationStep("Take three slow breaths. Feel your body sink into the bed.", 25),
            MeditationStep("Notice your feet and toes. Let them feel heavy and warm.", 30),
            MeditationStep("Move your attention to your legs. Let your calves and thighs relax.", 30),
            MeditationStep("Notice your stomach rising and falling as you breathe. Let it soften.", 30),
            MeditationStep("Relax your hands, your arms and your shoulders. Let them drop.", 30),
            MeditationStep("Soften your jaw, your cheeks and the space between your eyebrows.", 30),
            MeditationStep("Feel your whole body, heavy and still. Stay here as long as you like.", 40)
        )
    ),
    MeditationSession(
        title = "Let Go of the Day",
        shortName = "Let Go",
        intro = "Put down today's thoughts so your mind can rest.",
        steps = listOf(
            MeditationStep("Get comfortable and close your eyes. Breathe in slowly, and out slowly.", 20),
            MeditationStep("Think of one thing that went well today, however small.", 30),
            MeditationStep("Think of anything still on your mind. Picture placing it in a box for tomorrow.", 35),
            MeditationStep("Close the box. Tomorrow can take care of it.", 25),
            MeditationStep("If a thought comes back, notice it, then return to your breathing.", 35),
            MeditationStep("With each out-breath, let your body get a little heavier.", 35),
            MeditationStep("Rest here. There is nothing else you need to do tonight.", 40)
        )
    ),
    MeditationSession(
        title = "Count Down to Sleep",
        shortName = "Count Down",
        intro = "Give your busy mind one simple, calm job.",
        steps = listOf(
            MeditationStep("Lie comfortably and close your eyes. Let your breathing slow down.", 20),
            MeditationStep("Breathe out and silently count 100.", 20),
            MeditationStep("Keep counting down by one with each out-breath: 99, 98, 97…", 45),
            MeditationStep("If you lose count, that's fine. Just start again from where you remember.", 40),
            MeditationStep("Let the numbers get slower and quieter in your mind.", 45),
            MeditationStep("If you feel sleepy, let the counting go. Drift off.", 40)
        )
    )
)

//relaxing sounds
data class NatureSound(
    val name: String,
    val description: String,
    val resId: Int          // the file, e.g. R.raw.rain
)

val natureSounds = listOf(
    NatureSound("Rain", "Steady rainfall", R.raw.rain),
    NatureSound("Ocean waves", "Waves rolling onto the shore", R.raw.ocean),
    NatureSound("Forest", "Birds and rustling leaves", R.raw.forest),
    NatureSound("Campfire", "A crackling fire", R.raw.campfire)
)