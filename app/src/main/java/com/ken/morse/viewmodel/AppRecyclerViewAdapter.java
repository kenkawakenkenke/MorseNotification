package com.ken.morse.viewmodel;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ken.morse.BR;
import com.ken.morse.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppRecyclerViewAdapter extends RecyclerView.Adapter<AppRecyclerViewAdapter.ItemViewHolder> {
  private RecyclerView boundView;
  private List<AppViewModel> appViewModels;
  private List<AppViewModel> servingAppViewModels;

  public AppRecyclerViewAdapter(List<AppViewModel> appViewModels, RecyclerView boundView) {
    this.boundView = boundView;
    this.appViewModels = appViewModels;
    this.servingAppViewModels = new ArrayList<>(appViewModels);
    boundView.setAdapter(this);

    this.resortItems();
  }

  @Override
  public int getItemCount() {
    return servingAppViewModels.size();
  }

  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater
        .from(parent.getContext()).inflate(R.layout.app_recycler_item, parent, false);
    return new ItemViewHolder(v);
  }

  public void resortItems() {
    List<AppViewModel> newServingAppViewModels = new ArrayList<>(appViewModels);
    Collections.sort(newServingAppViewModels);
    this.servingAppViewModels = newServingAppViewModels;

    boundView.post(new Runnable() {
      @Override
      public void run() {
        notifyDataSetChanged();
      }
    });
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    AppViewModel viewModel = servingAppViewModels.get(position);
    holder.getBinding().setVariable(BR.viewModel, viewModel);
    holder.getBinding().executePendingBindings();
  }

  // ViewHolder
  public static class ItemViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding mBinding;

    public ItemViewHolder(View v) {
      super(v);
      mBinding = DataBindingUtil.bind(v);
    }

    public ViewDataBinding getBinding() {
      return mBinding;
    }
  }
}
