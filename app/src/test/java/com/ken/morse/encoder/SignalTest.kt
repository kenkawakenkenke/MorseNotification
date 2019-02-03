package com.ken.morse.encoder

import com.ken.morse.encoder.Signal.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class SignalTest {

    @Test
    fun signalsForCharacter() {
        assertEquals(
                listOf(DOT, SIGNAL_SPACER, DASH)
                , Signal.signalsForCharacter('a'))
        assertEquals(
                listOf(DOT, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH),
                Signal.signalsForCharacter('1'))
    }
    @Test
    fun signalsForCharacter_missing() {
       assertNull(Signal.signalsForCharacter('*'))
    }

}