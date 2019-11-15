package com.demo.pagingwithnetwork.base;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

public abstract class BaseActivity<V extends ViewDataBinding,K extends ViewModel> extends AppCompatActivity {

    private V binding = null;
    private K viewModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
        init();
    }

    public abstract void onCreate();

    public abstract void init();

    public void setView(@LayoutRes int layoutResID){
        binding = DataBindingUtil.setContentView(this,layoutResID);
    }

    public V getBinding() {
        return binding;
    }

    public K getViewModel() {
        return viewModel;
    }

    public void setViewModel(K viewModel) {
        this.viewModel = viewModel;
    }
}
