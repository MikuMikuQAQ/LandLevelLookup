package com.demo.pagingwithnetwork.ui.county;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.demo.pagingwithnetwork.R;
import com.demo.pagingwithnetwork.base.BaseFragment;
import com.demo.pagingwithnetwork.databinding.FragmentCountyBinding;
import com.demo.pagingwithnetwork.ui.MainActivity;
import com.demo.pagingwithnetwork.ui.county.adapter.CountyAdapter;
import com.demo.pagingwithnetwork.util.ViewModelProviderUtil;

public class CountyFragment extends BaseFragment<FragmentCountyBinding,CountyViewModel> {

    @Override
    public View getView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_county,container,false);
    }

    @Override
    public void init(@Nullable View view) {
        setBinding();
        setViewModel(ViewModelProviderUtil.create().getCountyModelFactory());
        getViewModel().setBundle(getArguments());

        getBinding().setViewModel(getViewModel());

        CountyAdapter adapter = new CountyAdapter();
        getBinding().countyList.setAdapter(adapter);

        getViewModel().getAllCounty().observe(this,o->{
            adapter.submitList(o);
            getBinding().refreshList.setRefreshing(false);
        });
        getViewModel().cityName.observe(this,o->((MainActivity) getActivity()).setTopBar(o,"区/县"));

        getBinding().refreshList.setOnRefreshListener(()->getViewModel().refreshList());
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
        getViewModel().getCityName();
    }
}
