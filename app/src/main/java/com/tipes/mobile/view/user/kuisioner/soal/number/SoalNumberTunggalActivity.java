package com.tipes.mobile.view.user.kuisioner.soal.number;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivitySoalNumberTunggalBinding;
import com.tipes.mobile.model.instrumen.ModelInstrumenList;
import com.tipes.mobile.viewmodel.ViMoQuiz;

import java.util.List;

public class SoalNumberTunggalActivity extends AppCompatActivity {

    private ActivitySoalNumberTunggalBinding binding;
    private Bundle extras;
    private  int intIdKategori;
    private String sIdKategori, sNamaKategori;

    private ViMoQuiz mViMoQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalNumberTunggalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        extras = getIntent().getExtras();

        sIdKategori = extras.getString(String.valueOf(R.string.idkategori));
        sNamaKategori = extras.getString(String.valueOf(R.string.kategori));
        intIdKategori = Integer.parseInt(sIdKategori);

        settingToolbar();
        mViMoQuiz = ViewModelProviders.of(this).get(ViMoQuiz.class);



        binding.layoutSoal.setVisibility(View.INVISIBLE);
        mViMoQuiz.getInstrumen().observe(this, data -> {
            if (data != null)
            {
                setDataToView(data.getDataListst());

                binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
                binding.layoutSoal.setVisibility(View.VISIBLE);
            } else {

                binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
            }
        });

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