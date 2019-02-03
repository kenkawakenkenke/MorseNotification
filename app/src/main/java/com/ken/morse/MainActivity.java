package com.ken.morse;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.ken.morse.databinding.ActivityMainBinding;
import com.ken.morse.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
  private MainViewModel mainViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActivityMainBinding layoutBinding = DataBindingUtil.setContentView
        (this, R.layout.activity_main);

    mainViewModel = new MainViewModel(this, layoutBinding);
    layoutBinding.setViewModel(mainViewModel);
  }


  @Override
  protected void onResume() {
    super.onResume();

    // Invalidate since some permissions may have changed outside of the app.
    mainViewModel.notifyChange();
  }
}
