package com.tipes.mobile.view.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemDialogListCenterTopBinding;
import com.tipes.mobile.model.sekolah.ModelSekolahList;

import java.util.ArrayList;
import java.util.List;

public class DialogSekolahAdapter extends RecyclerView.Adapter<DialogSekolahAdapter.MainHolder> implements Filterable {
    private List<ModelSekolahList> mList, mListFilter;
    private static ClickListener clickListener;

    public DialogSekolahAdapter(List<ModelSekolahList> mList, List<ModelSekolahList> mList2) {
        this.mList = mList;
        this.mListFilter = mList2;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDialogListCenterTopBinding binding = ItemDialogListCenterTopBinding.inflate(layoutInflater, parent , false);
        return new MainHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        ModelSekolahList data = mListFilter.get(position);
        holder.mBinding.txtIsi.setText(data.getNamaSekolah());

        if (position == 0)
        {
            holder.mBinding.viewTop.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (mListFilter != null)
        {
            return mListFilter.size();
        } else
        {
            return 0;
        }
    }

    public ModelSekolahList getItemPosition(int position)
    {
        return mListFilter.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mListFilter = mList;
                } else {
                    List<ModelSekolahList> filteredList = new ArrayList<>();
                    for (ModelSekolahList row : mList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNamaSekolah().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }

                    mListFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListFilter = (ArrayList<ModelSekolahList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        private ItemDialogListCenterTopBinding mBinding;
        public MainHolder(@NonNull ItemDialogListCenterTopBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;

            mBinding.containerItemDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && clickListener != null) {
                        clickListener.onItemClick(v, pos,  mListFilter);
                    }
                }
            });
        }
    }

    /**
     ===================================================================
     Fungsi Item Bisa di klik
     ===================================================================
     **/
    public void setOnItemClickListener(ClickListener clickListener) {
        DialogSekolahAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position, List<ModelSekolahList> mList);
    }

}
