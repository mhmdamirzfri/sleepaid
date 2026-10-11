package com.example.sleepaid

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class WaterCalculatorTest {

    @Test
    fun `60 kg low activity not hot gives 2100 ml`() {
        val result = WaterCalculator.dailyIntakeMl(60.0, ActivityLevel.LOW, hotWeather = false)
        assertEquals(2100, result)
    }

    @Test
    fun `70 kg high activity hot weather gives 3650 ml`() {
        val result = WaterCalculator.dailyIntakeMl(70.0, ActivityLevel.HIGH, hotWeather = true)
        assertEquals(3650, result)
    }

    @Test
    fun `glasses always round up`() {
        assertEquals(9, WaterCalculator.glassesFor(2100))
        assertEquals(8, WaterCalculator.glassesFor(2000))
    }

    @Test
    fun `valid weights are accepted`() {
        assertEquals(60.0, WaterCalculator.parseWeight("60")!!, 0.001)
        assertEquals(62.5, WaterCalculator.parseWeight(" 62,5 ")!!, 0.001)
        assertEquals(20.0, WaterCalculator.parseWeight("20")!!, 0.001)
        assertEquals(300.0, WaterCalculator.parseWeight("300")!!, 0.001)
    }

    @Test
    fun `invalid weights are rejected`() {
        assertNull(WaterCalculator.parseWeight("abc"))
        assertNull(WaterCalculator.parseWeight(""))
        assertNull(WaterCalculator.parseWeight("5"))
        assertNull(WaterCalculator.parseWeight("350"))
    }
}