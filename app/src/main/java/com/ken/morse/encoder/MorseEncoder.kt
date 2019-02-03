package com.ken.morse.encoder

import com.atilika.kuromoji.TokenizerBase
import com.atilika.kuromoji.ipadic.Tokenizer
import java.util.*

class MorseEncoder {
    /**
     * Encode free text into Morse signals.
     */
    fun encode(origText: String): EncodeResult {
        val normalizedText = normalize(origText)

        val wordSignals = ArrayList<List<Signal>>()
        for (word in normalizedText.split(Regex("[ 　]"))) {
            if (word.isEmpty()) {
                continue
            }
            var wordSignal = encodeWord(word)
            if (wordSignal.isEmpty()) {
                continue
            }
            wordSignals.add(wordSignal)
        }
        return EncodeResult(origText, normalizedText, flatten(wordSignals, Signal.WORD_SPACER))
    }

    /**
     * Encode a single word
     */
    private fun encodeWord(word : String): List<Signal> {
        val letterSignals = ArrayList<List<Signal>>()
        for(letterIdx in word.indices) {
            letterSignals.add(Signal.signalsForCharacter(word[letterIdx])?: continue)
        }
        return flatten(letterSignals, Signal.LETTER_SPACER)
    }

    companion object {
        private fun normalize(origWord: String): String {
            var word = origWord
            word = word.trim { it <= ' ' }
            word = word.toLowerCase()
            word = toPronunciation(word)

            return word
        }

        /**
         * Flattens a list of list of objects into a list of objects, with each original list separated by {@code separator}. Assumes that each list is non-empty.
         */
        private fun <T> flatten(lists : List<List<T>>, separator: T) : List<T> {
            var flat : MutableList<T> = mutableListOf()
            for(i in lists.indices) {
                flat.addAll(lists[i])
                if (i+1 < lists.size) {
                    flat.add(separator)
                }
            }
            return flat
        }

        /**
         * Convert text that may include japanese text to its pronunciation. Leaves alphanumerics as
         * is.
         * e.g: "SOSを受信しましたYO" -> "SOSヲジュシンシマシタYO"
         */
        private fun toPronunciation(text: String): String {
            val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
            val tokens = tokenizer.tokenize(text)
            val pronunciations = StringBuffer()
            for (token in tokens) {
                if (token.reading == "*") {
                    // Typically when the token is not japanese.
                    pronunciations.append(token.surface)
                } else {
                    pronunciations.append(token.pronunciation)
                }
            }
            return pronunciations.toString()
        }
    }
}
