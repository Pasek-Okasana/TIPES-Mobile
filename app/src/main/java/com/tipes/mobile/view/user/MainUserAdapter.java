package com.tipes.mobile.view.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.databinding.ItemMenuDashboardBinding;
import com.tipes.mobile.model.MenuDashboardModel;

import java.util.List;

public class MainUserAdapter extends RecyclerView.Adapter<MainUserAdapter.MainHolder> {
    private List<MenuDashboardModel> mList;
    private static ClickListener clickListener;

    public MainUserAdapter(List<MenuDashboardModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMenuDashboardBinding binding = ItemMenuDashboardBinding.inflate(layoutInflater, parent, false);
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

    public MenuDashboardModel getItemPosition(int position)
    {
        return  mList.get(position);
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        private ItemMenuDashboardBinding mBinding;
        public MainHolder(ItemMenuDashboardBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.cardMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && clickListener != null) {
                        clickListener.onCardClick(view, pos);
                    }
                }
            });
        }

        public void bindToView(MenuDashboardModel itemPosition, int position) {
            mBinding.imgLogo.setImageResource(itemPosition.getGambar());
            mBinding.txtNama.setText(itemPosition.getNama());
            mBinding.txtKeterangan.setText(itemPosition.getKeterangan());
        }
    }

    /**
     ===================================================================
     Fungsi Item Bisa di klik
     ===================================================================
     **/
    public void setOnItemClickListener(ClickListener clickListener) {
        MainUserAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onCardClick(View v, int position);
    }
}
