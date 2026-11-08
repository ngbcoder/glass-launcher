package com.voidlauncher.data

import org.junit.Assert.assertTrue
import org.junit.Test

class TracksTest {
    @Test
    fun tracksNonEmpty() {
        assertTrue(Tracks.list.isNotEmpty())
        assertTrue(Tracks.list.all { it.durationSec > 0 })
    }
}
