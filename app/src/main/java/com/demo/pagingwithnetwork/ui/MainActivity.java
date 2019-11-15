package com.demo.pagingwithnetwork.ui;

import androidx.lifecycle.ViewModel;
import com.demo.pagingwithnetwork.R;
import com.demo.pagingwithnetwork.base.BaseActivity;
import com.demo.pagingwithnetwork.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, ViewModel> {

    @Override
    public void onCreate() {
        setView(R.layout.activity_main);
    }

    @Override
    public void init() {
        setTopBar("中国","省/直辖市");
    }

    public void setTopBar(String title,String subtitle) {
        if (title != null) getBinding().topBar.setTitle(title);
        if (subtitle != null) getBinding().topBar.setSubtitle(subtitle);

        setSupportActionBar(getBinding().topBar);
    }
}
