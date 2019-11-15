package com.demo.pagingwithnetwork.ui.city.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import com.demo.pagingwithnetwork.R;
import com.demo.pagingwithnetwork.base.BaseAdapter;
import com.demo.pagingwithnetwork.base.BaseViewHolder;
import com.demo.pagingwithnetwork.data.model.City;
import com.demo.pagingwithnetwork.databinding.ItemBaseBinding;
import com.demo.pagingwithnetwork.ui.city.CityFragmentDirections;

public class CityAdapter extends BaseAdapter<City,ItemBaseBinding> {

    private static DiffUtil.ItemCallback<City> diffCallback = new DiffUtil.ItemCallback<City>() {
        @Override
        public boolean areItemsTheSame(@NonNull City oldItem, @NonNull City newItem) {
            return oldItem.getCityCode() == newItem.getCityCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull City oldItem, @NonNull City newItem) {
            return oldItem == newItem;
        }
    };

    /**
     * Creates a PagedListAdapter with default threading and
     * {@link ListUpdateCallback}.
     * <p>
     * Convenience for {@link #PagedListAdapter(AsyncDifferConfig)}, which uses default threading
     * behavior.
     *
     * @param diffCallback The {@link DiffUtil.ItemCallback DiffUtil.ItemCallback} instance to
     *                     compare items in the list.
     */
    public CityAdapter() {
        super(diffCallback);
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public BaseViewHolder<ItemBaseBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base,parent,false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ItemBaseBinding> holder, int position) {
        if (getItem(position) != null) {
            holder.getBinding().itemText.setText(getItem(position).getCityName());
            holder.getBinding().getRoot().setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(CityFragmentDirections.actionCityFragmentToCountyFragment().setProvinceId(getItem(position).getProvinceId()).setCityId(getItem(position).getCityCode()));
            });
        }
    }
}
