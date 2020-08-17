package com.tipes.mobile.view.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemDialogListCenterTopBinding;
import com.tipes.mobile.model.ModelJenisKelamin;

import java.util.List;

public class DialogJenisKelaminAdapter extends RecyclerView.Adapter<DialogJenisKelaminAdapter.MainHolder> {
    private List<ModelJenisKelamin> mList;
    private static ClickListener clickListener;

    public DialogJenisKelaminAdapter(List<ModelJenisKelamin> mList) {
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
        ModelJenisKelamin data = mList.get(position);
        holder.mBinding.txtIsi.setText(data.getJk());

        if (position == 0)
        {
            holder.mBinding.viewTop.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (mList != null)
        {
            return mList.size();
        } else
        {
            return 0;
        }
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
        DialogJenisKelaminAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position, List<ModelJenisKelamin> mList);
    }

}
