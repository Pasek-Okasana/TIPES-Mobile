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
import com.tipes.mobile.databinding.FragmentBagianBBinding;
import com.tipes.mobile.model.ListPetunjukModel;

import java.util.ArrayList;
import java.util.List;

public class BagianBFragment extends Fragment {

    private FragmentBagianBBinding binding;
    private List<ListPetunjukModel> mList;
    private LinearLayoutManager mLayoutManager;
    private ListPetunjukFragAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBagianBBinding.inflate(inflater, container , false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        mList.clear();
        mList.add(new ListPetunjukModel(getString(R.string.BagianB_List1)));
        mList.add(new ListPetunjukModel(getString(R.string.BagianB_List2)));

        mAdapter = new ListPetunjukFragAdapter(mList);
        mLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerPetunjukB.setHasFixedSize(true);

        binding.recyclerPetunjukB.setAdapter(mAdapter);
        binding.recyclerPetunjukB.setLayoutManager(mLayoutManager);

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