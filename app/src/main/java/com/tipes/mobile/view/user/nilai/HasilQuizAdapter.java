package com.tipes.mobile.view.user.nilai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemNilaiDeskripsiBinding;
import com.tipes.mobile.model.hasilaksi.ModelHasilQuizData_Deskripsi;

import java.util.List;

public class HasilQuizAdapter extends RecyclerView.Adapter<HasilQuizAdapter.HasilViewHolder> {
    private List<ModelHasilQuizData_Deskripsi> mList;

    public HasilQuizAdapter(List<ModelHasilQuizData_Deskripsi> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public HasilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNilaiDeskripsiBinding binding = ItemNilaiDeskripsiBinding.inflate(layoutInflater, parent, false);
        return new HasilViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilViewHolder holder, int position) {
        ModelHasilQuizData_Deskripsi des = mList.get(position);
        holder.binding.txtNilaiTipe.setText(mList.get(position).getTipe());
        holder.binding.txtNilaiKepribadian.setText(mList.get(position).getKepribadian());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ModelHasilQuizData_Deskripsi getItemPosition(int position){
        return mList.get(position);
    }

    public class HasilViewHolder extends RecyclerView.ViewHolder {
        ItemNilaiDeskripsiBinding binding;
        public HasilViewHolder(@NonNull ItemNilaiDeskripsiBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
