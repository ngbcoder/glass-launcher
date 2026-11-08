package com.voidlauncher.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SearchLogicTest {
    @Test
    fun timerCommandParsed() {
        val r = SearchLogic.resultsFor("timer 5")
        assertTrue(r.any { it is SearchResult.Timer && it.seconds == 5 })
    }

    @Test
    fun mathParsed() {
        val r = SearchLogic.resultsFor("2 + 3")
        val calc = r.filterIsInstance<SearchResult.Calc>().firstOrNull()
        assertEquals("5", calc?.result)
    }

    @Test
    fun emptyReturnsEmpty() {
        assertEquals(0, SearchLogic.resultsFor("").size)
        assertEquals(0, SearchLogic.resultsFor("   ").size)
    }

    @Test
    fun webFallbackIncluded() {
        val r = SearchLogic.resultsFor("xyznonexistent")
        assertTrue(r.any { it is SearchResult.Web })
    }
}
