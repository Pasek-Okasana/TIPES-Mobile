package com.tipes.mobile.view.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemDialogListCenterTopBinding;
import com.tipes.mobile.model.sekolah.ModelJurusanList;

import java.util.ArrayList;
import java.util.List;

public class DialogJurusanAdapter extends RecyclerView.Adapter<DialogJurusanAdapter.MainHolderJ> implements Filterable {
    private List<ModelJurusanList> mList, mListFilter;
    private static ClickListener clickListener;
    private int pilihanjurusan;

    public DialogJurusanAdapter(List<ModelJurusanList> mList , int pilihanjurusan) {
        this.mList = mList;
        mListFilter = mList;
        this.pilihanjurusan = pilihanjurusan;
    }

    @NonNull
    @Override
    public MainHolderJ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDialogListCenterTopBinding binding = ItemDialogListCenterTopBinding.inflate(layoutInflater, parent , false);
        return new MainHolderJ(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolderJ holder, int position) {
        ModelJurusanList data = mListFilter.get(position);
        holder.mBinding.txtIsi.setText(data.getNamaJurusan());

        if (pilihanjurusan == 1)
        {
            if (position == 0)
            {
                holder.mBinding.viewTop.setVisibility(View.INVISIBLE);
            }
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

    public void removeAts(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
//        notifyItemChanged(position);
//        notifyItemRangeRemoved(position, mList.size());
        notifyItemRangeChanged(position, mList.size());
    }

    public ModelJurusanList getItemPosition(int position)
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
                    List<ModelJurusanList> filteredList = new ArrayList<>();
                    for (ModelJurusanList row : mList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNamaJurusan().toLowerCase().contains(charString.toLowerCase()) ) {
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
                mListFilter = (ArrayList<ModelJurusanList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MainHolderJ extends RecyclerView.ViewHolder {
        private ItemDialogListCenterTopBinding mBinding;
        public MainHolderJ(@NonNull ItemDialogListCenterTopBinding itemView) {
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
        DialogJurusanAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position, List<ModelJurusanList> mList);
    }

}
