package com.ken.morse.encoder

public data class EncodeResult (val origText : String, val encodedText : String, val signals : List<Signal>) {

    /**
     * Utility to convert a list of signals to duration specs that can be sent to a {@link Vibrator}.
     */
    fun toDuration(unitDurationMs: Long): LongArray {
        var durations: MutableList<Long> = mutableListOf()

        // Duration spec starts with the silence duration.
        durations.add(0);

        for (signal in signals) {
            // The duration spec mandates that the even elements represent the audible section.
            val lastElementAudible = (durations.size) % 2 == 0

            if (lastElementAudible != signal.audible) {
                durations.add(0)
            }
            durations.set(durations.size - 1, durations[durations.size - 1] + signal.durationUnits * unitDurationMs)
        }

        return durations.toLongArray()
    }

    override fun toString(): String {
        return signals.map({ it -> it.friendlyString }).joinToString("")
    }
}