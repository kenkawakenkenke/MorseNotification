package com.ken.morse.encoder

import com.ken.morse.encoder.Signal.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class MorseEncoderTest {

    val encoder = MorseEncoder()

    @Test
    fun encode() {
        assertEquals("Upper case normalized",
                EncodeResult(
                        "SOS",
                        "sos",
                        listOf(DOT, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DOT, LETTER_SPACER, DASH, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH, LETTER_SPACER, DOT, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DOT)
                ),
                encoder.encode("SOS"))

        assertEquals("Two words",
                EncodeResult(
                        "a b",
                        "a b",
                        listOf(DOT, SIGNAL_SPACER, DASH, WORD_SPACER, DASH, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DOT)
                ),
                encoder.encode("a b"))

        assertEquals("Multiple spaces ignored",
               EncodeResult(
                       "a  　 b",
                       "a  　 b",
                       listOf(DOT, SIGNAL_SPACER, DASH, WORD_SPACER, DASH, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DOT)),
                encoder.encode("a  　 b"))

        assertEquals("Numerics",
                EncodeResult("a1",
                        "a1",listOf(DOT, SIGNAL_SPACER, DASH, LETTER_SPACER, DOT, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH)),
                encoder.encode("a1"))

        assertEquals("Unknown character (*) ignored",
               EncodeResult("a*","a*",listOf(DOT, SIGNAL_SPACER, DASH)),
                encoder.encode("a*"))
    }

    @Test
    fun encodeKana() {
        // －・－－（ケ）　・－・－・（ン）
        assertEquals("Kana gets converted to pronunciation",
                EncodeResult(
                        "健",
                        "ケン",
                        listOf(DASH, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DASH, LETTER_SPACER,
                        DOT, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DOT, SIGNAL_SPACER, DASH, SIGNAL_SPACER, DOT)),
                encoder.encode("健"))
    }

}