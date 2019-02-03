package com.ken.morse.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.Bindable;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.ken.morse.MorseNotifier;
import com.ken.morse.base.ObservableViewModel;
import com.ken.morse.databinding.ActivityMainBinding;
import com.ken.morse.model.App;
import com.ken.morse.model.Notifier;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ObservableViewModel {
  private static final String TAG = "MorseNotification";

  private final SharedPreferences prefs;
  private final Activity activity;
  private final MorseNotifier morseNotifier;
  private final Notifier notifier;
  private final AppRecyclerViewAdapter appRecyclerViewAdapter;

  public MainViewModel(Activity activity, ActivityMainBinding layoutBinding) {
    this.activity = activity;
    this.morseNotifier = new MorseNotifier(activity);

    prefs = PreferenceManager.getDefaultSharedPreferences(activity);

    this.notifier = new Notifier(activity, prefs);
    List<AppViewModel> appViewModels = new ArrayList<>();
    for(App app : this.notifier.apps()) {
      appViewModels.add(new AppViewModel(activity, app, prefs, this));
    }
    appRecyclerViewAdapter = new AppRecyclerViewAdapter(appViewModels, layoutBinding.appsRecycler);
  }

  public void invalidateRecycler() {
    appRecyclerViewAdapter.resortItems();
  }

  @Bindable
  public Boolean getNotificationListenerAccessEnabled() {
    // From https://stackoverflow.com/questions/24145343/checking-android-system-security-notification-access-setting-programmatically
    return Settings.Secure.getString(activity.getContentResolver(),"enabled_notification_listeners").contains(activity.getApplicationContext().getPackageName());
  }

  @Bindable
  public Boolean getNotificationEnabled() {
    return notifier.isGlobalNotificationEnabled();
  }

  public void onNotificationEnabledChanged(CompoundButton buttonView, boolean isChecked) {
    notifier.setIsGlobalNotificationEnabled(isChecked);
    super.notifyChange();
  }

  public void openSettings(View view) {
    Intent callGPSSettingIntent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
    activity.startActivity(callGPSSettingIntent);
  }
}
