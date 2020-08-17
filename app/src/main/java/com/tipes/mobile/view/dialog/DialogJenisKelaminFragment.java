package com.tipes.mobile.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tipes.mobile.R;
import com.tipes.mobile.databinding.FragmentDialogJenisKelaminBinding;
import com.tipes.mobile.model.ModelJenisKelamin;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogJenisKelaminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogJenisKelaminFragment extends DialogFragment {
    private FragmentDialogJenisKelaminBinding binding;

    private List<ModelJenisKelamin> mListJk = new ArrayList<>();

    private DialogJenisKelaminAdapter mAdapter;
    private LinearLayoutManager mLayoutM;
    private RegisterOnClickListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogJenisKelaminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JenisKelaminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogJenisKelaminFragment newInstance(String param1, String param2) {
        DialogJenisKelaminFragment fragment = new DialogJenisKelaminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
        mListJk.add(new ModelJenisKelamin("1", getString(R.string.LakiLaki)));
        mListJk.add(new ModelJenisKelamin("2", getString(R.string.Perempuan)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDialogJenisKelaminBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new DialogJenisKelaminAdapter(mListJk);
        mLayoutM = new LinearLayoutManager(getContext());

        binding.recyclerDialog.setHasFixedSize(true);
        binding.recyclerDialog.setAdapter(mAdapter);
        binding.recyclerDialog.setLayoutManager(mLayoutM);

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new DialogJenisKelaminAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position, List<ModelJenisKelamin> mList) {
                listener = (RegisterOnClickListener) getActivity();
                listener.onItemPilihJK(position, mList);
                dismiss();
            }
        });

    }
}