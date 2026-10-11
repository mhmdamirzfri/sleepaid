package com.example.sleepaid

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalTime

class SleepCalculatorTest {

    @Test
    fun `gives four suggestions`() {
        val results = SleepCalculator.bedtimesFor(LocalTime.of(7, 0))
        assertEquals(4, results.size)
    }

    @Test
    fun `wake up at 7 am gives correct bedtimes`() {
        val results = SleepCalculator.bedtimesFor(LocalTime.of(7, 0))

        assertEquals(LocalTime.of(21, 46), results[0].time)
        assertEquals(LocalTime.of(23, 16), results[1].time)
        assertEquals(LocalTime.of(0, 46), results[2].time)
        assertEquals(LocalTime.of(2, 16), results[3].time)
    }

    @Test
    fun `bedtime at 11 pm wraps past midnight`() {
        val results = SleepCalculator.wakeTimesFor(LocalTime.of(23, 0))

        assertEquals(LocalTime.of(8, 14), results[0].time)
    }

    @Test
    fun `5 and 6 cycles are recommended but 3 and 4 are not`() {
        val results = SleepCalculator.bedtimesFor(LocalTime.of(7, 0))

        assertTrue(results[0].isRecommended)
        assertTrue(results[1].isRecommended)
        assertFalse(results[2].isRecommended)
        assertFalse(results[3].isRecommended)

        assertEquals(9.0, results[0].hoursOfSleep, 0.001)
        assertEquals(7.5, results[1].hoursOfSleep, 0.001)
    }
}