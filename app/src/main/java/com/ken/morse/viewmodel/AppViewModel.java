package com.ken.morse.viewmodel;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import com.ken.morse.base.ObservableViewModel;
import com.ken.morse.model.App;

public class AppViewModel extends ObservableViewModel implements Comparable<AppViewModel> {
  private static final String TAG = "MorseNotification";

  private final Activity activity;
  private final SharedPreferences prefs;
  private final App app;
  private final MainViewModel parentViewModel;

  public AppViewModel(Activity activity, App app, SharedPreferences prefs, MainViewModel parentViewModel) {
    this.activity = activity;
    this.prefs = prefs;
    this.app = app;
    this.parentViewModel = parentViewModel;
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
    if (isChecked == getNotificationEnabled()) {
      return;
    }
    app.setIsNotificationEnabled(isChecked);
    super.notifyChange();
    parentViewModel.invalidateRecycler();
  }

  @Override
  public int compareTo(AppViewModel other) {
    boolean enabled = getNotificationEnabled();
    boolean otherEnabled = other.getNotificationEnabled();
    if (enabled!=otherEnabled) {
      // Enabled apps come first.
      return -Boolean.compare(enabled, otherEnabled);
    }
    return app.appID.compareTo(other.app.appID);
  }
}
