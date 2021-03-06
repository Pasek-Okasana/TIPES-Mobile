package com.tipes.mobile.view.user.kuisioner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.databinding.ActivityKuisionerBinding;
import com.tipes.mobile.model.kategory.ModelKategoriList;
import com.tipes.mobile.view.user.kuisioner.soal.number.SoalNumberTunggalActivity;
import com.tipes.mobile.view.user.kuisioner.soal.yesno.SoalActivity;
import com.tipes.mobile.viewmodel.ViMoQuiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KuisionerActivity extends AppCompatActivity {
    private String TAG = KuisionerActivity.class.getSimpleName();
    private ActivityKuisionerBinding binding;
    private ViMoQuiz mViMoQuiz;

    private KuisionerAdapter mAdapter;
    private LinearLayoutManager mLayout;
    private List<ModelKategoriList> mList = new ArrayList<>();

    private SharedPrefManager mSPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKuisionerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        settingToolbar();
        mViMoQuiz = ViewModelProviders.of(this).get(ViMoQuiz.class);
        mSPM = new SharedPrefManager(this);
        mAdapter = new KuisionerAdapter(mList);
        mLayout = new LinearLayoutManager(this);

//        binding.recycleKuisioner.setHasFixedSize(true);
        binding.recycleKuisioner.setAdapter(mAdapter);
        binding.recycleKuisioner.setLayoutManager(mLayout);

        mViMoQuiz.getKategori().observe(this, data -> {
            binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
            if (data != null)
            {
                mList.clear();
                mList.addAll(data.getDataListst());
                mAdapter.notifyDataSetChanged();
                makeLogI("Jumlah Kategori  " + mList.size());
                makeLogI("Jumlah Kategori di Adapter " + mAdapter.getItemCount());

            } else {
                makeToast(getString(R.string.MaafJaringanSibuk));
            }
        });


        onClicked();
    }

    private void onClicked() {
        String sNilaiAct, sUsername;
        sNilaiAct = "simpanhasil";
        sUsername = mSPM.getSPUsername();
        Map<String, String> parameter = new HashMap<>();
        parameter.put("module", "hasil" );
        parameter.put("act", sNilaiAct);
        parameter.put("username", sUsername);
        parameter.put("kick", "1");
        parameter.put("parameter", "RSA" );
        parameter.put("lulus", "0" );

        binding.btnAksiHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViMoQuiz.postAksiHasil(parameter).observe(KuisionerActivity.this, data -> {
                    if (data != null){
                        if (data.getCode() == 201){
                            makeSnack("Berhasil Disimpan... Silahkan Pilih Menu Hasil Pada Halaman Home Untuk Melihat Hasil Kuisioner Yang Diisi !");
                        } else {
                            makeSnack("Maaf... Data Gagal Disimpan !");
                        }
                    } else {
                        makeSnack(getString(R.string.maafjaringansibuk));
                    }
                });
            }
        });

        mAdapter.setOnItemClickListener(new KuisionerAdapter.ClickListener() {
            @Override
            public void onCardClick(View v, int position) {
               int num = Integer.parseInt(mAdapter.getItemPosition(position).getIdKategori());
               if (num == 4)
               {
                   if (mSPM.getStatusD()){
                       startActivity(
                               new Intent(KuisionerActivity.this, SoalNumberTunggalActivity.class)
                                       .putExtra(
                                               String.valueOf(R.string.idkategori), mAdapter.getItemPosition(position).getIdKategori()
                                       )
                                       .putExtra(
                                               String.valueOf(R.string.kategori), mAdapter.getItemPosition(position).getNamaKategori()
                                       )
                                       .putExtra(
                                               String.valueOf(R.string.waktu), mAdapter.getItemPosition(position).getWaktu()
                                       )
                       );
                   } else {
                       makeSnack("Anda Sudah Mengisi Kuisioner ! Jika ingin mengikuti kuisioner ulang, hubungi admin !");
                   }

               } else if (num == 1){
                    if (mSPM.getStatusA()){
                        openSoalActivity(mAdapter, position);
                    } else {
                        makeSnack("Anda Sudah Mengisi Kuisioner ! Jika ingin mengikuti kuisioner ulang, hubungi admin !");
                    }
                } else if (num == 2){
                    if (mSPM.getStatusB()){
                        openSoalActivity(mAdapter, position);
                    } else {
                        makeSnack("Anda Sudah Mengisi Kuisioner ! Jika ingin mengikuti kuisioner ulang, hubungi admin !");
                    }
                } else if (num == 3){
                    if (mSPM.getStatusC()){
                        openSoalActivity(mAdapter, position);
                    } else {
                        makeSnack("Anda Sudah Mengisi Kuisioner ! Jika ingin mengikuti kuisioner ulang, hubungi admin !");
                    }
                }
//                makeSnack(mAdapter.getItemPosition(position).getNamaKategori());
            }
        });
    }

    private void openSoalActivity(KuisionerAdapter mAdapter, int position) {
        startActivity(
                new Intent(KuisionerActivity.this, SoalActivity.class)
                        .putExtra(
                                String.valueOf(R.string.idkategori), mAdapter.getItemPosition(position).getIdKategori()
                        )
                        .putExtra(
                                String.valueOf(R.string.kategori), mAdapter.getItemPosition(position).getNamaKategori()
                        )
                        .putExtra(
                                String.valueOf(R.string.waktu), mAdapter.getItemPosition(position).getWaktu()
                        )
        );
    }

    private void settingToolbar() {
        Toolbar mToolbar = (Toolbar) binding.toolbar.getRoot();
        setSupportActionBar(mToolbar);
        binding.toolbar.txtToolbarName.setText("");

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
    private void makeLogI(String msg)
    {
        Log.i(TAG, msg);
    }
    private void makeLogD(String msg)
    {
        Log.d(TAG, msg);
    }
}