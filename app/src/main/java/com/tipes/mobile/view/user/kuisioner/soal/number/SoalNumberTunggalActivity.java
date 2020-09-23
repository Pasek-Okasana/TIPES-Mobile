package com.tipes.mobile.view.user.kuisioner.soal.number;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.Helper.UtilsData;
import com.tipes.mobile.R;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.databinding.ActivitySoalNumberTunggalBinding;
import com.tipes.mobile.model.instrumen.ModelInstrumenList;
import com.tipes.mobile.viewmodel.ViMoQuiz;

import java.util.List;

public class SoalNumberTunggalActivity extends AppCompatActivity {

    private ActivitySoalNumberTunggalBinding binding;
    private Bundle extras;
    private  int intIdKategori, intWaktuSoal;
    private String sIdKategori, sNamaKategori, sNilai1R, sNilai2I, sNilai3A, sNilai4S, sNilai5E, sNilai6K;
    private SharedPrefManager mSPM;
    private ViMoQuiz mViMoQuiz;

    private static final long START_TIME_IN_MILLIS = 60000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalNumberTunggalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        extras = getIntent().getExtras();
        mSPM = new SharedPrefManager(this);
        sIdKategori = extras.getString(String.valueOf(R.string.idkategori));
        sNamaKategori = extras.getString(String.valueOf(R.string.kategori));
        intIdKategori = Integer.parseInt(sIdKategori);
        intWaktuSoal = Integer.parseInt(extras.getString(String.valueOf(R.string.waktu)));

        settingToolbar();
        mViMoQuiz = ViewModelProviders.of(this).get(ViMoQuiz.class);

        binding.layoutSoal.setVisibility(View.INVISIBLE);
        mViMoQuiz.getInstrumen().observe(this, data -> {
            if (data != null)
            {
                setDataToView(data.getDataListst());
            } else {

                binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
            }
        });

        setOnClick();
    }

    private void setOnClick() {
        binding.opsi1R.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.opsi_r1 :
                        sNilai1R = "1";
                        break;
                    case R.id.opsi_r2 :
                        sNilai1R = "2";
                        break;
                    case R.id.opsi_r3 :
                        sNilai1R = "3";
                        break;
                    case R.id.opsi_r4 :
                        sNilai1R = "4";
                        break;
                    case R.id.opsi_r5 :
                        sNilai1R = "5";
                        break;
                    case R.id.opsi_r6 :
                        sNilai1R = "6";
                        break;
                    case R.id.opsi_r7 :
                        sNilai1R = "7";
                        break;
                }
            }
        });

        binding.opsi2I.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.opsi_i1 :
                        sNilai2I = "1";
                        break;
                    case R.id.opsi_i2 :
                        sNilai2I = "2";
                        break;
                    case R.id.opsi_i3 :
                        sNilai2I = "3";
                        break;
                    case R.id.opsi_i4 :
                        sNilai2I = "4";
                        break;
                    case R.id.opsi_i5 :
                        sNilai2I = "5";
                        break;
                    case R.id.opsi_i6 :
                        sNilai2I = "6";
                        break;
                    case R.id.opsi_i7 :
                        sNilai2I = "7";
                        break;
                }
            }
        });

        binding.opsi3A.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                    case R.id.opsi_a1 :
                        sNilai3A = "1";
                        break;
                    case R.id.opsi_a2 :
                        sNilai3A = "2";
                        break;
                    case R.id.opsi_a3 :
                        sNilai3A = "3";
                        break;
                    case R.id.opsi_a4 :
                        sNilai3A = "4";
                        break;
                    case R.id.opsi_a5 :
                        sNilai3A = "5";
                        break;
                    case R.id.opsi_a6 :
                        sNilai3A = "6";
                        break;
                    case R.id.opsi_a7 :
                        sNilai3A = "7";
                        break;
                }
            }
        });

        binding.opsi4S.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                    case R.id.opsi_s1 :
                        sNilai4S = "1";
                        break;
                    case R.id.opsi_s2 :
                        sNilai4S = "2";
                        break;
                    case R.id.opsi_s3 :
                        sNilai4S = "3";
                        break;
                    case R.id.opsi_s4 :
                        sNilai4S = "4";
                        break;
                    case R.id.opsi_s5 :
                        sNilai4S = "5";
                        break;
                    case R.id.opsi_s6 :
                        sNilai4S = "6";
                        break;
                    case R.id.opsi_s7 :
                        sNilai4S = "7";
                        break;
                }
            }
        });

        binding.opsi5E.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                    case R.id.opsi_e1 :
                        sNilai5E = "1";
                        break;
                    case R.id.opsi_e2 :
                        sNilai5E = "2";
                        break;
                    case R.id.opsi_e3 :
                        sNilai5E = "3";
                        break;
                    case R.id.opsi_e4 :
                        sNilai5E = "4";
                        break;
                    case R.id.opsi_e5 :
                        sNilai5E = "5";
                        break;
                    case R.id.opsi_e6 :
                        sNilai5E = "6";
                        break;
                    case R.id.opsi_e7 :
                        sNilai5E = "7";
                        break;
                }
            }
        });

        binding.opsi6K.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                    case R.id.opsi_k1 :
                        sNilai6K = "1";
                        break;
                    case R.id.opsi_k2 :
                        sNilai6K = "2";
                        break;
                    case R.id.opsi_k3 :
                        sNilai6K = "3";
                        break;
                    case R.id.opsi_k4 :
                        sNilai6K = "4";
                        break;
                    case R.id.opsi_k5 :
                        sNilai6K = "5";
                        break;
                    case R.id.opsi_k6 :
                        sNilai6K = "6";
                        break;
                    case R.id.opsi_k7 :
                        sNilai6K = "7";
                        break;
                }

            }
        });

        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendDataToServer();
            }
        });
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

        makeSnack("Username " + sUsername + " Kategori " + sIdKategori +
                " R " + sNilai1R +
                " I " + sNilai2I +
                " A " + sNilai3A
        );
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis * intWaktuSoal, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = UtilsData.updateCountTimeDownString(millisUntilFinished);
                binding.txtWaktuNilai.setText(time);
                binding.layoutSoal.setVisibility(View.VISIBLE);
                binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
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


    private void setDataToView(List<ModelInstrumenList> dataListst) {

        for (ModelInstrumenList row : dataListst)
        {
            int numb = Integer.parseInt(row.getIdInstrument());
            switch (numb)
            {
                case 1 :
                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                           binding.txtPertanyaan1R.setText(data.getDataListst().get(0).getPertanyaan());
                        } else {

                        }
                    });
                    break;
                case 2 :
                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            binding.txtPertanyaan2I.setText(data.getDataListst().get(0).getPertanyaan());
                        } else {

                        }
                    });
                    break;
                case 3:
                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            binding.txtPertanyaan3A.setText(data.getDataListst().get(0).getPertanyaan());
                        } else {

                        }
                    });
                    break;
                case 4 :
                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            binding.txtPertanyaan4S.setText(data.getDataListst().get(0).getPertanyaan());
                        } else {

                        }
                    });

                    break;
                case 5:
                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            binding.txtPertanyaan5E.setText(data.getDataListst().get(0).getPertanyaan());
                        } else {

                        }
                    });
                    break;
                case 6 :
                    mViMoQuiz.getSoalYesNo(sIdKategori, row.getIdInstrument()).observe(this, data -> {
                        if (data != null)
                        {
                            binding.txtPertanyaan6K.setText(data.getDataListst().get(0).getPertanyaan());

                            startTimer();
                        } else {

                        }
                    });
                    break;
            }
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

    private boolean validasiSoal()
    {
        if (binding.txtPertanyaan1R.length() == 0 ||
                binding.txtPertanyaan2I.length() == 0 ||
                binding.txtPertanyaan3A.length() == 0 ||
                binding.txtPertanyaan4S.length() == 0 ||
                binding.txtPertanyaan5E.length() == 0 ||
                binding.txtPertanyaan6K.length() == 0 ){
            return false;
        } else {
            return true;
        }
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