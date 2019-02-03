package com.ken.morse.encoder

/**
 * Representation of a single Morse signal, which could be audible (dot/dash), or silent
 * (letter/word spacing).
 */
enum class Signal private constructor(var audible: Boolean, val durationUnits: Int, val friendlyString: String) {
    DOT(true, 1, "・"),
    DASH(true, 3, "－"),
    SIGNAL_SPACER(false, 1, ""),
    LETTER_SPACER(false, 3, " "),
    WORD_SPACER(false, 7, "  ");

    companion object {
        private val signalsForCharacter = HashMap<Char, List<Signal>>()

        fun signalsForCharacter(c: Char): List<Signal>? {
            return signalsForCharacter[c]
        }

        // Setter only used for initializing from a string spec.
        private fun addSignals(c: Char, signalSpec: String) {
            var signals: MutableList<Signal> = mutableListOf()
            for (i in signalSpec.indices) {
                signals.add(if (signalSpec[i] == '・') DOT else DASH)
                if (i + 1 < signalSpec.length) {
                    signals.add(SIGNAL_SPACER)
                }
            }
            signalsForCharacter[c] = signals
        }

        init {
            addSignals('a', "・－")
            addSignals('b', "－・・・")
            addSignals('c', "－・－・")
            addSignals('d', "－・・")
            addSignals('e', "・")
            addSignals('f', "・・－・")
            addSignals('g', "－－・")
            addSignals('h', "・・・・")
            addSignals('i', "・・")
            addSignals('j', "・－－－")
            addSignals('k', "－・－")
            addSignals('l', "・－・・")
            addSignals('m', "－－")
            addSignals('n', "－・")
            addSignals('o', "－－－")
            addSignals('p', "・－－・")
            addSignals('q', "－－・－")
            addSignals('r', "・－・")
            addSignals('s', "・・・")
            addSignals('t', "－")
            addSignals('u', "・・－")
            addSignals('v', "・・・－")
            addSignals('w', "・－－")
            addSignals('x', "－・・－")
            addSignals('y', "－・－－")
            addSignals('z', "－－・・")

            addSignals('1', "・－－－－")
            addSignals('2', "・・－－－")
            addSignals('3', "・・・－－")
            addSignals('4', "・・・・－")
            addSignals('5', "・・・・・")
            addSignals('6', "－・・・・")
            addSignals('7', "－－・・・")
            addSignals('8', "－－－・・")
            addSignals('9', "－－－－・")
            addSignals('0', "－－－－－")

            addSignals('.', "・－・－・－")
            addSignals(',', "－－・・－－")
            addSignals('?', "・・－－・・")
            addSignals('-', "－・・・・－")
            addSignals('/', "－・・－・")
            addSignals('@', "・－－・－・")
            addSignals('(', "－・－－・")
            addSignals(')', "－・－－・－")

            addSignals('イ',"・－")
            addSignals('ロ',"・－・－")
            addSignals('ハ',"－・・・")
            addSignals('ニ',"－・－・")
            addSignals('ホ',"－・・")
            addSignals('ヘ',"・")
            addSignals('ト',"・・－・・")
            addSignals('チ',"・・－・")
            addSignals('リ',"－－・")
            addSignals('ヌ',"・・・・")
            addSignals('ル',"－・－－・")
            addSignals('ヲ',"・－－－")
            addSignals('ワ',"－・－")
            addSignals('カ',"・－・・")
            addSignals('ヨ',"－－")
            addSignals('タ',"－・")
            addSignals('レ',"－－－")
            addSignals('ソ',"－－－・")
            addSignals('ツ',"・－－・")
            addSignals('ネ',"－－・－")
            addSignals('ナ',"・－・")
            addSignals('ラ',"・・・")
            addSignals('ム',"－")
            addSignals('ウ',"・・－")
            addSignals('ヰ',"・－・・－")
            addSignals('ノ',"・・－－")
            addSignals('オ',"・－・・・")
            addSignals('ク',"・・・－")
            addSignals('ヤ',"・－－")
            addSignals('マ',"－・・－")
            addSignals('ケ',"－・－－")
            addSignals('フ',"－－・・")
            addSignals('コ',"－－－－")
            addSignals('エ',"－・－－－")
            addSignals('テ',"・－・－－")
            addSignals('ア',"－－・－－")
            addSignals('サ',"－・－・－")
            addSignals('キ',"－・－・・")
            addSignals('ユ',"－・・－－")
            addSignals('メ',"－・・・－")
            addSignals('ミ',"・・－・－")
            addSignals('シ',"－－・－・")
            addSignals('ヱ',"・－－・・")
            addSignals('ヒ',"－－・・－")
            addSignals('モ',"－・・－・")
            addSignals('セ',"・－－－・")
            addSignals('ス',"－－－・－")
            addSignals('ン',"・－・－・")
            addSignals('゛',"・・")
            addSignals('゜',"・・－－・")
        }

    }
}
