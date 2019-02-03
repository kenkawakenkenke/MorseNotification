package com.ken.morse.encoder

import com.ken.morse.encoder.Signal.*
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class EncodeResultTest {

    @Test
    fun testToString() {
        assertEquals(
                "・ －  ・",
                EncodeResult("","",listOf(DOT, LETTER_SPACER, DASH, WORD_SPACER, DOT)).toString())
    }

    @Test
    fun toDuration_simple() {
        assertArrayEquals(
                longArrayOf(0, 100, 300, 100),
                EncodeResult("","",listOf(DOT,LETTER_SPACER, DOT)).toDuration(100))
    }

    @Test
    fun toDuration_mergeRecurring() {
        assertArrayEquals(
                longArrayOf(0, 300, 1000),
                EncodeResult("","",listOf(DOT,DOT, DOT, LETTER_SPACER, WORD_SPACER)).toDuration(100)
        )
    }


}