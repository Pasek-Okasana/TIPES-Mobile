package com.tipes.mobile.view.user.kuisioner.soal.yesno;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.Helper.UtilsData;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivitySoalBinding;
import com.tipes.mobile.model.instrumen.ModelInstrumenList;
import com.tipes.mobile.model.soal.yesno.ModelSoalYNList;
import com.tipes.mobile.view.user.kuisioner.soal.SoalYNOnClickListener;
import com.tipes.mobile.viewmodel.ViMoQuiz;

import java.util.ArrayList;
import java.util.List;

public class SoalActivity extends AppCompatActivity implements SoalYNOnClickListener {
    private ActivitySoalBinding binding;
    private Bundle extras;
    private  int intIdKategori;
    private String sIdKategori,sNamaKategori;

    private ViMoQuiz mViMoQuiz;

    private SoalYN1Adapter mAdapterSN1, mAdapterSN2, mAdapterSN3, mAdapterSN4, mAdapterSN5 , mAdapterSN6;
    private List<ModelSoalYNList> mListSN1 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN2 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN3 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN4 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN5 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN6 = new ArrayList<>();

    private LinearLayoutManager mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        extras = getIntent().getExtras();

        sIdKategori = extras.getString(String.valueOf(R.string.idkategori));
        intIdKategori = Integer.parseInt(sIdKategori);
        sNamaKategori = extras.getString(String.valueOf(R.string.kategori));
        settingToolbar();
        mViMoQuiz = ViewModelProviders.of(this).get(ViMoQuiz.class);

        mViMoQuiz.getInstrumen().observe(this, data -> {
            binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
            if (data != null)
            {
                setDataToView(data.getDataListst());
            } else {

            }
        });

    }

    private void setDataToView(List<ModelInstrumenList> dataListst) {
        for (ModelInstrumenList row : dataListst)
        {
            int numb = Integer.parseInt(row.getIdInstrument());
            mLayout = new LinearLayoutManager(this);
            switch (numb)
            {
                case 1 :
                    binding.txtSoal1.setText(row.getNamaInstrument());
                    mAdapterSN1 = new SoalYN1Adapter(mListSN1, this, "R");

                    binding.recycleviewSoal1.setHasFixedSize(true);

                    binding.recycleviewSoal1.setLayoutManager(mLayout);
                    binding.recycleviewSoal1.setAdapter(mAdapterSN1);

                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            mListSN1.clear();
                            mListSN1.addAll(data.getDataListst());
                            mAdapterSN1.notifyDataSetChanged();
                        } else {

                        }
                    });
                    break;
                case 2 :
                    binding.txtSoal2.setText(row.getNamaInstrument());
                    mAdapterSN2 = new SoalYN1Adapter(mListSN2, this, "I");

                    binding.recycleviewSoal2.setHasFixedSize(true);

                    binding.recycleviewSoal2.setLayoutManager(mLayout);
                    binding.recycleviewSoal2.setAdapter(mAdapterSN2);

                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            mListSN2.clear();
                            mListSN2.addAll(data.getDataListst());
                            mAdapterSN2.notifyDataSetChanged();
                        } else {

                        }
                    });
                    break;
                case 3:
                    binding.txtSoal3.setText(row.getNamaInstrument());
                    mAdapterSN3 = new SoalYN1Adapter(mListSN3, this, "A");

                    binding.recycleviewSoal3.setHasFixedSize(true);

                    binding.recycleviewSoal3.setLayoutManager(mLayout);
                    binding.recycleviewSoal3.setAdapter(mAdapterSN3);

                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            mListSN3.clear();
                            mListSN3.addAll(data.getDataListst());
                            mAdapterSN3.notifyDataSetChanged();
                        } else {

                        }
                    });
                    break;
                case 4 :
                    binding.txtSoal4.setText(row.getNamaInstrument());
                    mAdapterSN4 = new SoalYN1Adapter(mListSN4, this, "S");

                    binding.recycleviewSoal4.setHasFixedSize(true);

                    binding.recycleviewSoal4.setLayoutManager(mLayout);
                    binding.recycleviewSoal4.setAdapter(mAdapterSN4);

                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            mListSN4.clear();
                            mListSN4.addAll(data.getDataListst());
                            mAdapterSN4.notifyDataSetChanged();
                        } else {

                        }
                    });
                    break;
                case 5:
                    binding.txtSoal5.setText(row.getNamaInstrument());
                    mAdapterSN5 = new SoalYN1Adapter(mListSN5, this, "E");

                    binding.recycleviewSoal5.setHasFixedSize(true);

                    binding.recycleviewSoal5.setLayoutManager(mLayout);
                    binding.recycleviewSoal5.setAdapter(mAdapterSN5);

                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            mListSN5.clear();
                            mListSN5.addAll(data.getDataListst());
                            mAdapterSN5.notifyDataSetChanged();
                        } else {

                        }
                    });
                    break;
                case 6 :
                    binding.txtSoal6.setText(row.getNamaInstrument());
                    mAdapterSN6 = new SoalYN1Adapter(mListSN6, this, "K");

                    binding.recycleviewSoal6.setHasFixedSize(true);

                    binding.recycleviewSoal6.setLayoutManager(mLayout);
                    binding.recycleviewSoal6.setAdapter(mAdapterSN6);

                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            mListSN6.clear();
                            mListSN6.addAll(data.getDataListst());
                            mAdapterSN6.notifyDataSetChanged();
                        } else {

                        }
                    });
                    break;
            }
        }
    }



    @Override
    public void onItemSoal1Click(int position, int id, List<ModelSoalYNList> mList, String instrumen) {

        String totalya = UtilsData.hitungTotalYA(mList);
        String totaltidak = UtilsData.hitungTotaTidak(mList);
        if (instrumen.equals("R"))
        {
//            makeToast("R");
            binding.txtTotalYaSoal1.setText(totalya);
            binding.txtTotalTidakSoal1.setText(totaltidak);
        } else if (instrumen.equals("I"))
        {
//            makeToast("I");
            binding.txtTotalYaSoal2.setText(totalya);
            binding.txtTotalTidakSoal2.setText(totaltidak);
        } else if (instrumen.equals("A"))
        {
//            makeToast("A");
            binding.txtTotalYaSoal3.setText(totalya);
            binding.txtTotalTidakSoal3.setText(totaltidak);
        } else if (instrumen.equals("S"))
        {
//            makeToast("S");
            binding.txtTotalYaSoal4.setText(totalya);
            binding.txtTotalTidakSoal4.setText(totaltidak);
        } else if (instrumen.equals("E"))
        {
//            makeToast("E");
            binding.txtTotalYaSoal5.setText(totalya);
            binding.txtTotalTidakSoal5.setText(totaltidak);
        } else if (instrumen.equals("K"))
        {
//            makeToast("K");
            binding.txtTotalYaSoal6.setText(totalya);
            binding.txtTotalTidakSoal6.setText(totaltidak);
        }
    }

    private void settingToolbar() {
        Toolbar mToolbar = (Toolbar) binding.toolbar.getRoot();
        setSupportActionBar(mToolbar);
        binding.toolbar.txtToolbarName.setText(sNamaKategori);

        mToolbar.setNavigationIcon(R.drawable.ic_backspace_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });
    }
    /**
     ==================== Make Toast
     */
    private void makeToast(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    private void makeSnack(String msg){
        Snackbar snackbar1 = Snackbar
                .make(binding.getRoot(), msg, Snackbar.LENGTH_LONG)
                .setDuration(6000);

        snackbar1.show();
        snackbar1.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar1.dismiss();
            }
        });
    }

//    private void makeLogI(String msg)
//    {
//        Log.i(TAG, msg);
//    }
//    private void makeLogD(String msg)
//    {
//        Log.d(TAG, msg);
//    }
}