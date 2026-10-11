package com.example.sleepaid

import java.time.LocalTime

data class Suggestion(
    val time: LocalTime,
    val cycles: Int
) {
    val hoursOfSleep: Double get() = cycles * 1.5
    val isRecommended: Boolean get() = cycles >= 5
}

object SleepCalculator {

    const val CYCLE_MINUTES = 90
    const val FALL_ASLEEP_MINUTES = 14

    private val CYCLE_OPTIONS = listOf(6, 5, 4, 3)


    fun bedtimesFor(wakeUp: LocalTime): List<Suggestion> =
        CYCLE_OPTIONS.map { cycles ->
            val minutes = cycles * CYCLE_MINUTES + FALL_ASLEEP_MINUTES
            Suggestion(wakeUp.minusMinutes(minutes.toLong()), cycles)
        }

    fun wakeTimesFor(bedtime: LocalTime): List<Suggestion> =
        CYCLE_OPTIONS.map { cycles ->
            val minutes = FALL_ASLEEP_MINUTES + cycles * CYCLE_MINUTES
            Suggestion(bedtime.plusMinutes(minutes.toLong()), cycles)
        }
}