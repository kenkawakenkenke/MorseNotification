package com.ken.morse

import android.os.Handler
import android.preference.PreferenceManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.ken.morse.model.Notifier
import java.util.regex.Pattern

/**
 * Service to intercept and parse notifications, to send to {@link MorseNotifier}.
 */
class NotificationIntercepterService : NotificationListenerService() {

    private var notifier : Notifier? = null
    private var morseNotifier: MorseNotifier? = null
    private var handler: Handler? = null

    override fun onCreate() {
        super.onCreate()

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        notifier = Notifier(this, prefs)

        handler = Handler()
        morseNotifier = MorseNotifier(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        val text = sbn.notification.tickerText ?: return

        if (notifier == null) {
            Log.d(TAG, "Notifier not set yet")
            return;
        }

        if (!notifier!!.isGlobalNotificationEnabled()) {
            Log.d(TAG, "Global notification disabled")
            return;
        }

        val app = notifier!!.appForId(packageName)
        if (app == null) {
            // Notifications from unknown apps shouldn't generally happen.
            Log.d(TAG, "Unknown app: $packageName")
            return
        }
        if (!app.isNotificationEnabled) {
            Log.d(TAG, "App $packageName hasn't allowed notifications")
            return
        }

        var message = parseNotification(packageName, text.toString()) ?: return
        if (message.isEmpty()) {
            return;
        }
        // Crop really long messages.
        message = message.substring(0, Math.min(message.length,10))
        Log.d(TAG, "message: [$message]")

        // Delay the vibration to allow time for any system vibrations.
        handler!!.postDelayed({ morseNotifier!!.notifyText(message) }, 2000)
    }

    companion object {
        private val TAG = "MorseNotification"

        private fun parseNotification(packageName: String, rawText: String): String? {
            // Special case for Twitter.
            if (packageName.contains("com.twitter.android")) {
                return parseTwitterNotification(rawText)
            }
            return rawText
        }

        private fun parseTwitterNotification(rawText: String): String? {
            val retweetedBy = findGroup("(.*)がリツイート:", rawText, 1)
            if (retweetedBy != null) {
                return "rt"
            }

            val commentedBy = findGroup("(.*)がコメントをつけて引用しました:", rawText, 1)
            if (commentedBy != null) {
                return "rt"
            }

            val dmUser = findGroup("[^:]*: ?(.*)", rawText, 1)
            return dmUser
        }

        private fun findGroup(patternStr: String, text: String, group: Int): String? {
            val pat = Pattern.compile(patternStr)
            val matcher = pat.matcher(text)
            if (!matcher.find()) {
                return null;
            }
            return matcher.group(group);
        }
    }
}
