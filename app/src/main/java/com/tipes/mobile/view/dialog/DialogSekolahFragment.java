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
import com.tipes.mobile.databinding.FragmentDialogSekolahBinding;
import com.tipes.mobile.model.sekolah.ModelSekolahList;
import com.tipes.mobile.viewmodel.ViMoSekolah;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogSekolahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogSekolahFragment extends DialogFragment {

    private FragmentDialogSekolahBinding binding;

    private ViMoSekolah mVimoSekolah;

    private List<ModelSekolahList> mList, mList2;
    private DialogSekolahAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private RegisterOnClickListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IdSekolah = "IdSekolah";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String sIdSekolah;

    public DialogSekolahFragment() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static DialogSekolahFragment newInstance(String param1) {
        DialogSekolahFragment fragment = new DialogSekolahFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IdSekolah, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_IdSekolah);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDialogSekolahBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mVimoSekolah = ViewModelProviders.of(this).get(ViMoSekolah.class);
        binding.txtTopKetDialog.setText(getString(R.string.PilihSekolah));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txtKeterangan.setVisibility(View.INVISIBLE);

        mList = new ArrayList<>();
        mList2 = new ArrayList<>();
        mAdapter = new DialogSekolahAdapter(mList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerDialog.setHasFixedSize(true);

        binding.recyclerDialog.setAdapter(mAdapter);
        binding.recyclerDialog.setLayoutManager(mLayoutManager);

        mVimoSekolah.getSekolah().observe(getViewLifecycleOwner(), data -> {
            if (data != null)
            {
                binding.txtKeterangan.setVisibility(View.INVISIBLE);
                mList.clear();
                mList.addAll(data.getSekolahList());
                mAdapter.notifyDataSetChanged();
            } else
            {
                binding.recyclerDialog.setVisibility(View.INVISIBLE);
                binding.txtKeterangan.setVisibility(View.VISIBLE);
                binding.txtKeterangan.setText(getString(R.string.MaafJaringanSibuk));
            }
        });



        setFilterData();
        setOnClick();
//        makeToast(mParam1);
    }

    private void setFilterData() {
        binding.inTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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


//                makeLogI(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setOnClick() {
        mAdapter.setOnItemClickListener(new DialogSekolahAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position, List<ModelSekolahList> mList) {
                listener = (RegisterOnClickListener) getActivity();
                listener.onItemPilihSekolahClick(position, mList);
                dismiss();
            }
        });
    }

    /**
     * ============= On Binding Destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void makeToast(String msg)
    {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void makeLogI(String msg)
    {
        Log.i("Dialog Sekolah ", msg);
    }
}