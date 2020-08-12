package com.tipes.mobile.view.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemDialogListCenterTopBinding;
import com.tipes.mobile.model.sekolah.ModelSekolahList;

import java.util.List;

public class DialogSekolahAdapter extends RecyclerView.Adapter<DialogSekolahAdapter.MainHolder> {
    private List<ModelSekolahList> mList;
    private static ClickListener clickListener;

    public DialogSekolahAdapter(List<ModelSekolahList> mList) {
        this.mList = mList;
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
        ModelSekolahList data = mList.get(position);
        holder.mBinding.txtIsi.setText(data.getNamaSekolah());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ModelSekolahList getItemPosition(int position)
    {
        return mList.get(position);
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
                        clickListener.onItemClick(v, pos,  mList);
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
