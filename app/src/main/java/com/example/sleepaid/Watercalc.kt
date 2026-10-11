package com.example.sleepaid

import kotlin.math.ceil
import kotlin.math.roundToInt

enum class ActivityLevel(val label: String, val extraMl: Int) {
    LOW("Low (mostly sitting)", 0),
    MODERATE("Moderate (about 30 min exercise)", 350),
    HIGH("High (1 hour or more exercise)", 700)
}

object WaterCalculator {

    const val ML_PER_KG = 35
    const val HOT_WEATHER_ML = 500
    const val GLASS_ML = 250
    const val MIN_WEIGHT = 20.0
    const val MAX_WEIGHT = 300.0

    // weight × 35 + activity extra + hot weather extra
    fun dailyIntakeMl(weightKg: Double, activity: ActivityLevel, hotWeather: Boolean): Int {
        val base = (weightKg * ML_PER_KG).roundToInt()
        val heat = if (hotWeather) HOT_WEATHER_ML else 0
        return base + activity.extraMl + heat
    }

    fun glassesFor(ml: Int): Int = ceil(ml / GLASS_ML.toDouble()).toInt()

    fun parseWeight(text: String): Double? {
        val value = text.trim().replace(',', '.').toDoubleOrNull() ?: return null
        return if (value in MIN_WEIGHT..MAX_WEIGHT) value else null
    }
}