package com.tipes.mobile.view.user.kuisioner.soal.yesno;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ItemSoalYesNoBinding;
import com.tipes.mobile.model.soal.yesno.ModelSoalYNList;
import com.tipes.mobile.view.user.kuisioner.soal.SoalYNOnClickListener;

import java.util.List;

public class SoalYN1Adapter extends RecyclerView.Adapter<SoalYN1Adapter.MainHolder> {
    private List<ModelSoalYNList> mList;
    private SoalYNOnClickListener clickListener;
    private String instrumen;

    public SoalYN1Adapter(List<ModelSoalYNList> mList, SoalYNOnClickListener clickListener, String instrumen) {
        this.mList = mList;
        this.clickListener = clickListener;
        this.instrumen = instrumen;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSoalYesNoBinding binding = ItemSoalYesNoBinding.inflate(layoutInflater, parent, false);
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

    public ModelSoalYNList getItemPosition(int position)
    {
        return mList.get(position);
    }

    public List<ModelSoalYNList> getAllData()
    {
        return mList;
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        ItemSoalYesNoBinding mBinding;
        int opsi;
        public MainHolder(ItemSoalYesNoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            mBinding.opsi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int id) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && clickListener != null) {
                        switch (id)
                        {
                            case R.id.opsi_ya :
                                opsi = 1;
                                break;
                            case R.id.opsi_tidak :
                                opsi = 2;
                                break;
                        }

                        mList.get(pos).setNumber(opsi);
//                        Log.e("Tes Adapter : ", "Posisi : " + pos + " Nilai " + mList.get(pos).getNumber());
//                        notifyDataSetChanged();
                        clickListener.onItemSoal1Click(pos, opsi, mList, instrumen);
                    }

                }
            });

            mBinding.opsiYa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

        public void bindToView(ModelSoalYNList itemPosition, int position) {
            mBinding.txtPertanyaan.setText(itemPosition.getPertanyaan());
        }
    }
}
