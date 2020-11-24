package com.tipes.mobile.view.user.nilai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tipes.mobile.Helper.DokumenPDF;
import com.tipes.mobile.R;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.databinding.ActivityHasilBinding;
import com.tipes.mobile.model.hasilaksi.ModelHasilQuiz;
import com.tipes.mobile.model.hasilaksi.ModelHasilQuizData_Deskripsi;
import com.tipes.mobile.viewmodel.ViMoQuiz;
import com.tipes.mobile.viewmodel.ViMoUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HasilActivity extends AppCompatActivity {
    private String TAG = HasilActivity.class.getCanonicalName();
    private ActivityHasilBinding binding;
    private ViMoQuiz mViMoQuiz;

    private List<ModelHasilQuizData_Deskripsi> mList = new ArrayList<>();
    private HasilQuizAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private SharedPrefManager mSPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViMoUser mViMoUser = ViewModelProviders.of(this).get(ViMoUser.class);
        mViMoQuiz = ViewModelProviders.of(this).get(ViMoQuiz.class);
        settingToolbar();

        mSPM = new SharedPrefManager(getApplicationContext());

        mAdapter = new HasilQuizAdapter(mList);
        mLayoutManager = new LinearLayoutManager(this);
        binding.recycleviewDeskripsi.setHasFixedSize(true);

        binding.recycleviewDeskripsi.setAdapter(mAdapter);
        binding.recycleviewDeskripsi.setLayoutManager(mLayoutManager);

        // TODO : HIDE BUTTON
        binding.btnSimpanPdf.setVisibility(View.INVISIBLE);
        mViMoUser.getMyAkun().observe(HasilActivity.this, dataakun -> {
            if (dataakun != null){
                if (dataakun.getAkunList().get(0).getAsalSekolah() != null){
                    mViMoQuiz.getHasilAksi(dataakun.getAkunList().get(0).getAsalSekolah()).observe(HasilActivity.this, hasil -> {
                        if (hasil != null){
                            if (hasil.getCode() == 201){
                                binding.txtNilaiKeterangan.setText(hasil.getData().getKeterangan());
                                binding.txtNilaiKompentensi.setText(hasil.getData().getKompentensi());
                                binding.txtNilaiLulus.setText(hasil.getData().getLulus());

                                mList.clear();
                                mList.addAll(hasil.getData().getListDeskripsi());
                                mAdapter.notifyDataSetChanged();
                                makeLogI("Jumlah Data Des  " + mList.size());
                                makeLogI("Jumlah Data Des di Adapter " + mAdapter.getItemCount());
                                makeLogI("Jumlah Data Des Tipe di Adapter " + mAdapter.getItemPosition(0).getTipe());


                                binding.btnSimpanPdf.setVisibility(View.VISIBLE);
                                binding.btnSimpanPdf.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        requestStoragePermission(hasil);

                                    }
                                });
                            } else { // end hasil code
                                makeSnack("Gagal memuat data !");
                            }
                        } else { // end hasil null
                            makeSnack(getString(R.string.maafjaringansibuk));
                        }
                    });
                } else { // end if dataakun
                    makeSnack(getString(R.string.maafjaringansibuk));
                }
            } else { // end if dataakun null
                makeSnack(getString(R.string.maafjaringansibuk));
            }
        });

    }

    //    TODO : CEK PERMISION
    private void requestStoragePermission(ModelHasilQuiz hasil) {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            cetakDokumen(hasil);
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(),"Error occurred! ",Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void cetakDokumen(ModelHasilQuiz hasil) {
        //buat dokumentPDF
        DokumenPDF dokumenPDF = new DokumenPDF();
        dokumenPDF.createHasilQuiz(hasil, mSPM.getSPUsername(), this, binding.getRoot());
    }

    private void settingToolbar() {
        Toolbar mToolbar = (Toolbar) binding.toolbar.getRoot();
        setSupportActionBar(mToolbar);
        binding.toolbar.txtToolbarName.setText("Hasil Quiz");

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
    private void makeLogI(String msg)
    {
        Log.i(TAG, msg);
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
}