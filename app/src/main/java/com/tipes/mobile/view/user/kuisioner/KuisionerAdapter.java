package com.tipes.mobile.view.user.kuisioner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemMenuKategoriBinding;
import com.tipes.mobile.model.kategory.ModelKategoriList;

import java.util.List;

public class KuisionerAdapter extends RecyclerView.Adapter<KuisionerAdapter.MainHolder> {
    private List<ModelKategoriList> mList;
    private static ClickListener clickListener;

    public KuisionerAdapter(List<ModelKategoriList> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMenuKategoriBinding binding = ItemMenuKategoriBinding.inflate(layoutInflater, parent, false);
        return new MainHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        holder.bindToView(getItemPosition(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ModelKategoriList getItemPosition(int position)
    {
        return mList.get(position);
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        ItemMenuKategoriBinding mBinding;
        public MainHolder(ItemMenuKategoriBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            mBinding.btnKerjakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && clickListener != null) {
                        clickListener.onCardClick(view, pos);
                    }
                }
            });
        }

        public void bindToView(ModelKategoriList itemPosition, int position) {
            mBinding.txtNama.setText(itemPosition.getNamaKategori());
        }
    }

    /**
     ===================================================================
     Fungsi Item Bisa di klik
     ===================================================================
     **/
    public void
    setOnItemClickListener(ClickListener clickListener) {
       KuisionerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onCardClick(View v, int position);
    }
}
