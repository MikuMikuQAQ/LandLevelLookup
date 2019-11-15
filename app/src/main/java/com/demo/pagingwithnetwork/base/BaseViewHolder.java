package com.demo.pagingwithnetwork.base;


import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private V binding;

    public V getBinding() {
        return binding;
    }

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
