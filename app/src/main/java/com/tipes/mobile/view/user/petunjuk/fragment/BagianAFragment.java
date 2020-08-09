package com.tipes.mobile.view.user.petunjuk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tipes.mobile.R;
import com.tipes.mobile.databinding.FragmentBagianABinding;
import com.tipes.mobile.model.ListPetunjukModel;

import java.util.ArrayList;
import java.util.List;

public class BagianAFragment extends Fragment {

    private FragmentBagianABinding binding;
    private List<ListPetunjukModel> mList;
    private LinearLayoutManager mLayoutManager;
    private ListPetunjukFragAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBagianABinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // menambah data list
        mList = new ArrayList<>();
        mList.clear();
        mList.add(new ListPetunjukModel(getString(R.string.BagianA_List1)));
        mList.add(new ListPetunjukModel(getString(R.string.BagianA_List2)));

        mAdapter = new ListPetunjukFragAdapter(mList);
        mLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerPetunjukA.setHasFixedSize(true);

        binding.recyclerPetunjukA.setAdapter(mAdapter);
        binding.recyclerPetunjukA.setLayoutManager(mLayoutManager);

        mAdapter.notifyDataSetChanged();
    }

    /**
     * ============= On Binding Destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}