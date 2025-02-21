package com.andef.myvideos.presentation.parser

import junit.framework.TestCase.assertEquals
import org.junit.Test

class DurationParserTest {
    companion object {
        private const val UNKNOWN_DURATION = "Продолжительность: неизвестно"

        private const val DURATION_INITIAL_PHRASE = "Продолжительность: "
    }

    @Test
    fun `the duration is unknown`() {
        var duration = ""
        var parseDuration = DurationParser.parse(duration)
        assertEquals(UNKNOWN_DURATION, parseDuration)
        duration = "aflag"
        parseDuration = DurationParser.parse(duration)
        assertEquals(UNKNOWN_DURATION, parseDuration)
    }

    @Test
    fun `invalid format`() {
        val duration = "PT10H5M30S"
        val parseDuration = DurationParser.parse(duration)
        assertEquals(UNKNOWN_DURATION, parseDuration)
    }

    @Test
    fun `minutes less than 10 and second more than 10`() {
        val duration = "PT2M15S"
        val result = DurationParser.parse(duration)
        assertEquals("${DURATION_INITIAL_PHRASE}02:15", result)
    }

    @Test
    fun `minutes less than 10 and seconds less than 10`() {
        val duration = "PT3M5S"
        val result = DurationParser.parse(duration)
        assertEquals("${DURATION_INITIAL_PHRASE}03:05", result)
    }

    @Test
    fun `minutes more than 10 and second more than 10`() {
        val duration = "PT12M34S"
        val result = DurationParser.parse(duration)
        assertEquals("${DURATION_INITIAL_PHRASE}12:34", result)
    }

    @Test
    fun `minutes more than 10 and second less than 10`() {
        val duration = "PT10M9S"
        val result = DurationParser.parse(duration)
        assertEquals("${DURATION_INITIAL_PHRASE}10:09", result)
    }
}