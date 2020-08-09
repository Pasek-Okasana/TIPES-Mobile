package com.tipes.mobile.view.user.petunjuk.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemListPetunjukFragmentBinding;
import com.tipes.mobile.model.ListPetunjukModel;

import java.util.List;

public class ListPetunjukFragAdapter extends RecyclerView.Adapter<ListPetunjukFragAdapter.MainHolder> {
    private List<ListPetunjukModel> mList;

    public ListPetunjukFragAdapter(List<ListPetunjukModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemListPetunjukFragmentBinding binding = ItemListPetunjukFragmentBinding.inflate(layoutInflater,parent, false);
        return new MainHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        ListPetunjukModel data = mList.get(position);
        int no = position + 1;
        holder.mBinding.txtNo.setText(String.valueOf(no));
        holder.mBinding.txtKeterangan.setText(data.getNama());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MainHolder extends RecyclerView.ViewHolder {
        ItemListPetunjukFragmentBinding mBinding;
        public MainHolder(ItemListPetunjukFragmentBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
