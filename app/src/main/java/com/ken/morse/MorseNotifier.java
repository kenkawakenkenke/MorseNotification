package com.ken.morse;

import static android.content.Context.VIBRATOR_SERVICE;

import android.content.Context;
import android.os.Vibrator;
import com.ken.morse.encoder.EncodeResult;
import com.ken.morse.encoder.MorseEncoder;

/**
 * Handles playing notifications as morse code.
 */
public class MorseNotifier {

  private Vibrator mVibrator;
  private final MorseEncoder converter = new MorseEncoder();

  public MorseNotifier(Context context) {
    mVibrator = (Vibrator)context.getSystemService(VIBRATOR_SERVICE);
  }

  public EncodeResult notifyText(String text) {
    long unitSignalDurationMs = 100;
    EncodeResult encoded = converter.encode(text);

    mVibrator.vibrate(encoded.toDuration(unitSignalDurationMs), -1);
    return encoded;
  }
}
