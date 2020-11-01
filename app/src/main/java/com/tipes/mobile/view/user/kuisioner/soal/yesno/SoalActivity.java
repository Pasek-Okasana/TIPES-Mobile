package com.tipes.mobile.view.user.kuisioner.soal.yesno;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.Helper.UtilsData;
import com.tipes.mobile.R;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.databinding.ActivitySoalBinding;
import com.tipes.mobile.model.instrumen.ModelInstrumenList;
import com.tipes.mobile.model.soal.yesno.ModelSoalYNList;
import com.tipes.mobile.view.user.kuisioner.soal.SoalYNOnClickListener;
import com.tipes.mobile.viewmodel.ViMoQuiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoalActivity extends AppCompatActivity implements SoalYNOnClickListener {
    private ActivitySoalBinding binding;
    private Bundle extras;
    private  int intIdKategori, intWaktuSoal;
    private String sIdKategori,sNamaKategori, sNilai1R, sNilai2I, sNilai3A, sNilai4S, sNilai5E, sNilai6K;
    private SharedPrefManager mSPM;

    private ViMoQuiz mViMoQuiz;

    private SoalYN1Adapter mAdapterSN1, mAdapterSN2, mAdapterSN3, mAdapterSN4, mAdapterSN5 , mAdapterSN6;
    private List<ModelSoalYNList> mListSN1 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN2 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN3 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN4 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN5 = new ArrayList<>();
    private List<ModelSoalYNList> mListSN6 = new ArrayList<>();

    private LinearLayoutManager mLayout;


    private static final long START_TIME_IN_MILLIS = 60000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mSPM = new SharedPrefManager(this);
        extras = getIntent().getExtras();
        sIdKategori = extras.getString(String.valueOf(R.string.idkategori));
        intIdKategori = Integer.parseInt(sIdKategori);
        sNamaKategori = extras.getString(String.valueOf(R.string.kategori));
        intWaktuSoal = Integer.parseInt(extras.getString(String.valueOf(R.string.waktu)));

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

        setOnClick();
    }

    private void setOnClick() {
        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToServer();
            }
        });
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis * intWaktuSoal, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = UtilsData.updateCountTimeDownString(millisUntilFinished);
                binding.txtWaktuNilai.setText(time);
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                binding.txtWaktuNilai.setText(getString(R.string.WaktuHabis));
                finish();
            }
        }.start();
        mTimerRunning = true;
    }

    private void sendDataToServer() {
        if (sNilai1R == null)
        {
            sNilai1R = "0";
        }
        if (sNilai2I == null)
        {
            sNilai2I = "0";
        }
        if (sNilai3A == null)
        {
            sNilai3A = "0";
        }
        if (sNilai4S == null)
        {
            sNilai4S = "0";
        }
        if (sNilai5E == null)
        {
            sNilai5E = "0";
        }
        if (sNilai6K == null)
        {
            sNilai6K = "0";
        }

        String sNilaiAct, sUsername;
        sNilaiAct = "input-hasil_"+sIdKategori;
        sUsername = mSPM.getSPUsername();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("id_kategori", sIdKategori);
        parameter.put("yes_resultR", sNilai1R);
        parameter.put("yes_resultI",sNilai2I);
        parameter.put("yes_resultA", sNilai3A);
        parameter.put("yes_resultS", sNilai4S);
        parameter.put("yes_resultE", sNilai5E);
        parameter.put("yes_resultK", sNilai6K);
        parameter.put("kick", "0");
        parameter.put("module", "quiz");
        parameter.put("act", sNilaiAct);
        parameter.put("username", sUsername);
        parameter.put("R", "");
        parameter.put("I", "");
        parameter.put("A", "");
        parameter.put("S", "");
        parameter.put("E", "");
        parameter.put("K", "");
        parameter.put("kick", "");

        mViMoQuiz.postAksiQuiz(parameter).observe(this, datapost -> {
            if (datapost != null){
                if (datapost.getCode() == 201){
                    if (intIdKategori == 1){
                        mSPM.saveSPBoolean(String.valueOf(R.string.SP_STATUS_A), false);
                    } else if (intIdKategori == 2){
                        mSPM.saveSPBoolean(String.valueOf(R.string.SP_STATUS_B), false);
                    } else if (intIdKategori == 3){
                        mSPM.saveSPBoolean(String.valueOf(R.string.SP_STATUS_C), false);
                    }
                    finish();
                } else {
                    makeToast("Maaf... Gagal Menyimpan Data !");
                }
            } else {
                makeSnack(getString(R.string.maafjaringansibuk));
            }
        });
//        makeSnack("Username " + sNilaiAct + " Kategori " + sIdKategori +
//                " R " + sNilai1R +
//                        " I " + sNilai2I +
//                        " A " + sNilai3A
//        );
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
                            startTimer();
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
            sNilai1R = totalya;
            binding.txtTotalYaSoal1.setText(totalya);
            binding.txtTotalTidakSoal1.setText(totaltidak);
        } else if (instrumen.equals("I"))
        {
//            makeToast("I");
            sNilai2I = totalya;
            binding.txtTotalYaSoal2.setText(totalya);
            binding.txtTotalTidakSoal2.setText(totaltidak);
        } else if (instrumen.equals("A"))
        {
//            makeToast("A");
            sNilai3A = totalya;
            binding.txtTotalYaSoal3.setText(totalya);
            binding.txtTotalTidakSoal3.setText(totaltidak);
        } else if (instrumen.equals("S"))
        {
//            makeToast("S");\
            sNilai4S = totalya;
            binding.txtTotalYaSoal4.setText(totalya);
            binding.txtTotalTidakSoal4.setText(totaltidak);
        } else if (instrumen.equals("E"))
        {
//            makeToast("E");
            sNilai5E = totalya;
            binding.txtTotalYaSoal5.setText(totalya);
            binding.txtTotalTidakSoal5.setText(totaltidak);
        } else if (instrumen.equals("K"))
        {
//            makeToast("K");
            sNilai6K = totalya;
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