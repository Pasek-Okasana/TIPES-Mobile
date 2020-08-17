package com.tipes.mobile.view.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tipes.mobile.R;
import com.tipes.mobile.databinding.FragmentDialogJurusanBinding;
import com.tipes.mobile.model.sekolah.ModelJurusanList;
import com.tipes.mobile.viewmodel.ViMoSekolah;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogJurusanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogJurusanFragment extends DialogFragment {
    private FragmentDialogJurusanBinding binding;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pilihjurusan";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param2";
    private String TAG = DialogJurusanFragment.class.getSimpleName();

    private List<ModelJurusanList> mList, mListFilter;
    private ViMoSekolah mViMoSekolah;
    // TODO : Inisialisai Adapter
    private DialogJurusanAdapter mAdapter;
    private LinearLayoutManager mLayoutM;

    private RegisterOnClickListener listener;

    // TODO: Rename and change types of parameters
    private int mPilihJurusan, mPosition;
    private String mIDSekolah;

    public DialogJurusanFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static DialogJurusanFragment newInstance(int pilihjurusan, String id) {
        DialogJurusanFragment fragment = new DialogJurusanFragment();
        Bundle args = new Bundle();
        args.putInt(Integer.toString(R.string.PilihanJurusan), pilihjurusan);
        args.putString(Integer.toString(R.string.IDSekolah), id);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogJurusanFragment newInstanceFilterPosition(int pilihjurusan, int position,  List<ModelJurusanList> mListJurusan) {
        DialogJurusanFragment fragment = new DialogJurusanFragment();
        Bundle args = new Bundle();
        args.putInt(Integer.toString(R.string.PilihanJurusan), pilihjurusan);
        args.putInt(Integer.toString(R.string.Position), position);
        args.putSerializable(Integer.toString(R.string.ListJurusan), (Serializable) mListJurusan);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);

        mListFilter = new ArrayList<>();
        if (getArguments() != null) {
            mPilihJurusan = getArguments().getInt(Integer.toString(R.string.PilihanJurusan));
            mPosition = getArguments().getInt(Integer.toString(R.string.Position));
            mIDSekolah = getArguments().getString(Integer.toString(R.string.IDSekolah));
            mListFilter = (List<ModelJurusanList>) getArguments().getSerializable(Integer.toString(R.string.ListJurusan));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDialogJurusanBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mViMoSekolah = ViewModelProviders.of(this).get(ViMoSekolah.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        mAdapter = new DialogJurusanAdapter(mList, mPilihJurusan);
        mLayoutM = new LinearLayoutManager(getContext());


        makeLogI("Pilih Jurusan :  " + mPilihJurusan);
        setAdapter();
        setDataToView();

        setFilterData();
        setOnClick();

    }

    // TODO : Ketika Kolom Sekolah Dan Jurusan Ditekan
    private void setOnClick() {
        if (mPilihJurusan == 1)
        {
            mAdapter.setOnItemClickListener(new DialogJurusanAdapter.ClickListener() {
                @Override
                public void onItemClick(View v, int position, List<ModelJurusanList> mList) {

                    listener = (RegisterOnClickListener) getActivity();
                    listener.onItemPilihJurusan1(position, mList);
                    dismiss();
                }
            });

        } else  if (mPilihJurusan == 2)
        {
            mAdapter.setOnItemClickListener(new DialogJurusanAdapter.ClickListener() {
                @Override
                public void onItemClick(View v, int position, List<ModelJurusanList> mList) {

                    listener = (RegisterOnClickListener) getActivity();
                    listener.onItemPilihJurusan2(position, mList, false);
                    dismiss();
                }
            });

            binding.txtNoContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener = (RegisterOnClickListener) getActivity();
                    listener.onItemPilihJurusan2(0, mList, true);
                    dismiss();
                }
            });
        } else  if (mPilihJurusan == 3)
        {
            mAdapter.setOnItemClickListener(new DialogJurusanAdapter.ClickListener() {
                @Override
                public void onItemClick(View v, int position, List<ModelJurusanList> mList) {

                    listener = (RegisterOnClickListener) getActivity();
                    listener.onItemPilihJurusan3(position, mList, false);
                    dismiss();
                }
            });


            binding.txtNoContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener = (RegisterOnClickListener) getActivity();
                    listener.onItemPilihJurusan3(0, mList, true);
                    dismiss();
                }
            });
        }

    }

    private void setFilterData() {
        binding.inTxtSearchJur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAdapter.getFilter().filter(charSequence);
                mAdapter.notifyDataSetChanged();
                int countlist = mAdapter.getItemCount();
                if (countlist > 0)
                {
                    binding.txtKeterangan.setVisibility(View.INVISIBLE);

                } else
                {
                    binding.txtKeterangan.setVisibility(View.VISIBLE);
                    binding.txtKeterangan.setText(getString(R.string.MaafTidakDitemukan));
                }
                makeLogI("List Adapter " + countlist);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setDataToView() {

        binding.txtKeterangan.setVisibility(View.INVISIBLE);
        if (mPilihJurusan == 1)
        {
            // TODO : Untuk Menghilangkan View Tidak memilih Jurusan
            binding.txtNoContent.setVisibility(View.GONE);

            // TODO : Menggambil data Di View Model
            mViMoSekolah.getJurusan().observe(getViewLifecycleOwner(), data -> {
                if (data != null)
                {
                    binding.txtKeterangan.setVisibility(View.INVISIBLE);
                    List<ModelJurusanList> filteredList = new ArrayList<>();
                    // TODO: Untuk Memilah data berdasarkan id sekolah
                    for (ModelJurusanList row : data.getJurusanList()) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getIdSekolah().equals(mIDSekolah)) {
                            filteredList.add(row);
                        }
                    }
                    makeLogI("Size Filter List  " + filteredList.size());
                    mList.clear();
                    this.mList.addAll(filteredList);
                    mAdapter.notifyDataSetChanged();

                    // TODO: Untuk Memberitau Jika Data tidak Ada
                    int count = mAdapter.getItemCount();
                    if (count < 1)
                    {
                        binding.txtKeterangan.setVisibility(View.VISIBLE);
                        binding.txtKeterangan.setText("Maaf, Data Masih Kosong !");
                    }
                } else
                {
                    binding.recyclerDialog.setVisibility(View.INVISIBLE);
                    binding.txtKeterangan.setVisibility(View.VISIBLE);
                    binding.txtKeterangan.setText(getString(R.string.MaafJaringanSibuk));
                }
            });
//            makeToast(mListS.get(0).getNamaSekolah());
        } else  if (mPilihJurusan == 2)
        {
            mList.clear();
            this.mList.addAll(mListFilter);
            mAdapter.notifyDataSetChanged();
            mAdapter.removeAts(mPosition);
        } else  if (mPilihJurusan == 3)
        {
            mList.clear();
            this.mList.addAll(mListFilter);
            mAdapter.notifyDataSetChanged();
            mAdapter.removeAts(mPosition);
        }
    }

    private void setAdapter() {
        binding.recyclerDialog.setHasFixedSize(true);
        binding.recyclerDialog.setAdapter(mAdapter);
        binding.recyclerDialog.setLayoutManager(mLayoutM);
    }

    private void makeToast(String msg)
    {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void makeLogI(String msg)
    {
        Log.i(TAG, msg);
    }
}