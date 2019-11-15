package com.demo.pagingwithnetwork.ui.province.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import com.demo.pagingwithnetwork.R;
import com.demo.pagingwithnetwork.base.BaseAdapter;
import com.demo.pagingwithnetwork.base.BaseViewHolder;
import com.demo.pagingwithnetwork.data.model.Province;
import com.demo.pagingwithnetwork.databinding.ItemBaseBinding;
import com.demo.pagingwithnetwork.ui.province.ProvinceFragmentDirections;

public class ProvinceAdatper extends BaseAdapter<Province, ItemBaseBinding> {

    private static DiffUtil.ItemCallback<Province> diffCallback = new DiffUtil.ItemCallback<Province>() {
        @Override
        public boolean areItemsTheSame(@NonNull Province oldItem, @NonNull Province newItem) {
            return oldItem.getProvinceCode() == newItem.getProvinceCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Province oldItem, @NonNull Province newItem) {
            return oldItem == newItem;
        }
    };

    public ProvinceAdatper() {
        super(diffCallback);
    }

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
            holder.getBinding().itemText.setText(getItem(position).getProvinceName());
            holder.getBinding().getRoot().setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(ProvinceFragmentDirections.actionProvinceFragmentToCityFragment().setProvinceId(getItem(position).getProvinceCode()));
            });
        }
    }

}
