package com.demo.pagingwithnetwork.ui.province;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.demo.pagingwithnetwork.R;
import com.demo.pagingwithnetwork.base.BaseFragment;
import com.demo.pagingwithnetwork.databinding.FragmentProvinceBinding;
import com.demo.pagingwithnetwork.ui.MainActivity;
import com.demo.pagingwithnetwork.ui.province.adapter.ProvinceAdatper;
import com.demo.pagingwithnetwork.util.ViewModelProviderUtil;

public class ProvinceFragment extends BaseFragment<FragmentProvinceBinding,ProvinceViewModel> {

    @Override
    public View getView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_province,container,false);
    }

    @Override
    public void init(@Nullable View view) {
        setBinding();
        setViewModel(ViewModelProviderUtil.create().getProvinceModelFactory());

        getBinding().setViewModel(getViewModel());

        ProvinceAdatper adatper = new ProvinceAdatper();
        getBinding().provinceList.setAdapter(adatper);

        getViewModel().loadData().observe(this, o->{
            adatper.submitList(o);
            getBinding().refreshList.setRefreshing(false);
        });

        getBinding().refreshList.setOnRefreshListener(()->{
            getViewModel().refreshList();
        });
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setTopBar("中国","省/直辖市");
    }
}
