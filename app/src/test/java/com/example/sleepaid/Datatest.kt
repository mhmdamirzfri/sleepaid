package com.example.sleepaid

import com.example.sleepaid.data.articles
import com.example.sleepaid.data.breathingPatterns
import com.example.sleepaid.data.meditationSessions
import com.example.sleepaid.data.quizQuestions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DataTest {

    @Test
    fun `quiz has enough questions for a round of 10`() {
        assertTrue(quizQuestions.size >= 10)
    }

    @Test
    fun `every quiz answer points at a real option`() {
        quizQuestions.forEach { q ->
            assertEquals("4 options: ${q.question}", 4, q.options.size)
            assertTrue("Bad answer: ${q.question}", q.answerIndex in q.options.indices)
        }
    }

    @Test
    fun `article ids match their position and have sources`() {
        articles.forEachIndexed { position, article ->
            assertEquals(position, article.id)
            assertTrue("No sources: ${article.title}", article.sources.isNotEmpty())
        }
    }

    @Test
    fun `every breathing phase lasts longer than 0 seconds`() {
        breathingPatterns.forEach { pattern ->
            pattern.phases.forEach { phase ->
                assertTrue("${pattern.name} ${phase.label}", phase.seconds > 0)
            }
        }
    }

    @Test
    fun `every meditation session has steps`() {
        meditationSessions.forEach { session ->
            assertTrue("No steps: ${session.title}", session.steps.isNotEmpty())
        }
    }
}