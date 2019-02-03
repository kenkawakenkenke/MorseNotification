package com.ken.morse.viewmodel;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ken.morse.BR;
import com.ken.morse.R;
import java.util.List;

public class AppRecyclerViewAdapter extends RecyclerView.Adapter<AppRecyclerViewAdapter.ItemViewHolder> {
  private List<AppViewModel> appViewModels;

  // ViewHolder
  public static class ItemViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding mBinding;

    public ItemViewHolder(View v) {
      super(v);
      // Bind処理
      mBinding = DataBindingUtil.bind(v);
    }

    public ViewDataBinding getBinding() {
      return mBinding;
    }
  }

  public AppRecyclerViewAdapter(List<AppViewModel> appViewModels) {
    this.appViewModels = appViewModels;
  }

  @Override
  public int getItemCount() {
    return appViewModels.size();
  }

  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater
        .from(parent.getContext()).inflate(R.layout.app_recycler_item, parent, false);
    return new ItemViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    AppViewModel viewModel = appViewModels.get(position);
    holder.getBinding().setVariable(BR.viewModel, viewModel);
    holder.getBinding().executePendingBindings();
  }
}
