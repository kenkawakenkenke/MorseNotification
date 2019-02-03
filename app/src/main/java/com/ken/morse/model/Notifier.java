package com.ken.morse.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notifier {
  private static final String KEY_GLOBAL_ENABLE_NOTIFICATION = "global.notification.enabled";

  private final Context context;
  private final SharedPreferences prefs;
  private final List<App> apps;
  private final Map<String, App> appForId = new HashMap<>();

  public Notifier(Context context, SharedPreferences prefs) {
    this.context = context;
    this.prefs = prefs;
    this.apps = App.loadApps(context, prefs);
    for(App app : apps) {
      appForId.put(app.appID, app);
    }

    // Hacky async task to pre-load the expensive names and icons in the background...
    AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... voids) {
        for (App app : apps) {
          app.appName();
          app.drawable();
        }
        return null;
      }
    };
    task.execute();
  }

  public List<App> apps() {
    return apps;
  }

  public App appForId(String id) {
    return appForId.get(id);
  }

  public boolean isGlobalNotificationEnabled() {
    return prefs.getBoolean(KEY_GLOBAL_ENABLE_NOTIFICATION, true);
  }
  public void setIsGlobalNotificationEnabled(boolean enabled) {
    prefs.edit().putBoolean(KEY_GLOBAL_ENABLE_NOTIFICATION, enabled).commit();
  }
}
