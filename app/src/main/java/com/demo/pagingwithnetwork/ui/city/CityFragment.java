package com.demo.pagingwithnetwork.ui.city;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.demo.pagingwithnetwork.R;
import com.demo.pagingwithnetwork.base.BaseFragment;
import com.demo.pagingwithnetwork.databinding.FragmentCityBinding;
import com.demo.pagingwithnetwork.ui.MainActivity;
import com.demo.pagingwithnetwork.ui.city.adapter.CityAdapter;
import com.demo.pagingwithnetwork.util.ViewModelProviderUtil;

public class CityFragment extends BaseFragment<FragmentCityBinding,CityViewModel> {

    @Override
    public View getView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_city,container,false);
    }

    @Override
    public void init(@Nullable View view) {
        setBinding();
        setViewModel(ViewModelProviderUtil.create().getCityModelFactory());
        getViewModel().setId(getArguments());
        getViewModel().cityName.observe(this,o-> ((MainActivity) getActivity()).setTopBar(o,"市"));

        getBinding().setViewModel(getViewModel());

        CityAdapter adapter = new CityAdapter();
        getBinding().cityList.setAdapter(adapter);

        getViewModel().getAllCity().observe(this,o->{
            adapter.submitList(o);
            getBinding().refreshList.setRefreshing(false);
        });

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
        getViewModel().getTitle();
    }
}
