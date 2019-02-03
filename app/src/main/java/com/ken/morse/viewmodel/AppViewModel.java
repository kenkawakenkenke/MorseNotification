package com.ken.morse.viewmodel;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import com.ken.morse.model.App;
import com.ken.morse.base.ObservableViewModel;

public class AppViewModel extends ObservableViewModel {
  private static final String TAG = "MorseNotification";

  private final Activity activity;
  private final SharedPreferences prefs;
  private final App app;

  public AppViewModel(Activity activity, App app, SharedPreferences prefs) {
    this.activity = activity;
    this.prefs = prefs;
    this.app = app;
  }

  @Bindable
  public String getAppName() {
    return app.appName();
  }

  @Bindable
  public Drawable getIcon() {
    return app.drawable();
  }

  @Bindable
  public Boolean getNotificationEnabled() {
    return app.isNotificationEnabled();
  }

  public void onNotificationEnabledChanged(CompoundButton buttonView, boolean isChecked) {
    app.setIsNotificationEnabled(isChecked);

  }

}
