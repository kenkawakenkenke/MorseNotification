package com.ken.morse.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
  private static Set<String> defaultEnabledApps = new HashSet<>();
  static {
    // Twitter
    defaultEnabledApps.add("com.twitter.android");
    // Hangouts
    defaultEnabledApps.add("com.google.android.talk");
    // Facebook messenger
    defaultEnabledApps.add("com.facebook.orca");
    // Line
    defaultEnabledApps.add("jp.naver.line.android");
  }

  private final Context context;
  private final ResolveInfo appInfo;
  private final SharedPreferences prefs;
  public final String appID;
  private String appName;
  private Drawable drawable;

  private final boolean defaultEnablement;

  public App(Context context, ResolveInfo appInfo, SharedPreferences prefs) {
    this.context = context;
    this.appInfo = appInfo;
    this.appID = appInfo.activityInfo.packageName;
    this.prefs = prefs;

    this.defaultEnablement = defaultEnabledApps.contains(appID);
  }

  public String appName() {
    if (appName == null) {
      appName = appInfo.loadLabel(context.getPackageManager()).toString();
    }
    return appName;
  }

  public Drawable drawable() {
    if (drawable == null) {
      drawable = appInfo.loadIcon(context.getPackageManager());
    }
    return drawable;
  }

  private String notificationEnabledKey() {
    return "notification.enabled."+appID;
  }

  public boolean isNotificationEnabled() {
    return prefs.getBoolean(notificationEnabledKey(), defaultEnablement);
  }
  public void setIsNotificationEnabled(boolean enabled) {
    prefs.edit().putBoolean(notificationEnabledKey(), enabled).commit();
  }

  public static List<App> loadApps(Context context, SharedPreferences prefs) {
    List<App>apps=new ArrayList<>();
    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities(mainIntent, 0);
    for (ResolveInfo pkgApp : pkgAppsList) {
      apps.add(new App(context, pkgApp, prefs));
    }
    return apps;
  }
}
