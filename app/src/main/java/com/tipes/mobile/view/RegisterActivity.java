package com.tipes.mobile.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivityRegisterBinding;
import com.tipes.mobile.model.sekolah.ModelJurusanList;
import com.tipes.mobile.model.sekolah.ModelSekolahList;
import com.tipes.mobile.view.dialog.DialogJurusanFragment;
import com.tipes.mobile.view.dialog.DialogSekolahFragment;
import com.tipes.mobile.view.dialog.RegisterOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements RegisterOnClickListener {
    private ActivityRegisterBinding binding;
    private String sIDSekolah;
    private int sPositionJur1, sPositionJur2, sPositionJur3;
    private List<ModelSekolahList> mListSekolah = new ArrayList<>();
    private List<ModelJurusanList> mListJurusan = new ArrayList<>();
    private List<ModelJurusanList> mListJurusan2 = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getWindow().setStatusBarColor(R.color.colorGray);

        onClickButtonJurusan();

    }

    private void onClickButtonJurusan() {
        String isiJur1, isiJur2, isiJur3;

        isiJur2 = binding.txtJurusan2.getText().toString();
        isiJur3 = binding.txtJurusan3.getText().toString();

        binding.txtSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DialogSekolahFragment dsekolah = DialogSekolahFragment.newInstance("haha");
                dsekolah.show(fm, "DialogSekolah");
            }
        });

        binding.txtJurusan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isiSekolah;
                isiSekolah = binding.txtSekolah.getText().toString();
                if (isiSekolah.isEmpty())
                {
                    makeSnack("Silahkan Pilih Sekolah Terlebih Dahulu !");
                } else {
                    FragmentManager fm = getSupportFragmentManager();
                    DialogJurusanFragment djur = DialogJurusanFragment.newInstance(1, sIDSekolah );
                    djur.show(fm, "Dialog Jurusan 1");
                }
            }
        });

        binding.txtJurusan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isiJur1;
                isiJur1 = binding.txtJurusan1.getText().toString();
                if (isiJur1.isEmpty())
                {
                    makeSnack("Silahkan Pilih Jurusan Pertama Terlebih Dahulu !");
                } else {
                    FragmentManager fm = getSupportFragmentManager();
                    DialogJurusanFragment djur = DialogJurusanFragment.newInstanceFilterPosition(2, sPositionJur1, mListJurusan );
                    djur.show(fm, "Dialog Jurusan 2");
                }
            }
        });

        binding.txtJurusan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isiJur;
                isiJur = binding.txtJurusan2.getText().toString();
                if (isiJur.isEmpty())
                {
                    makeSnack("Silahkan Pilih Jurusan Kedua Terlebih Dahulu !");
                } else {
                    if (isiJur.equals(getString(R.string.TidakMemilih)))
                    {
                        makeSnack("Silahkan Pilih Jurusan Kedua Terlebih Dahulu !");
                    } else {
                        FragmentManager fm = getSupportFragmentManager();
                        DialogJurusanFragment djur = DialogJurusanFragment.newInstanceFilterPosition(3, sPositionJur2, mListJurusan2 );
                        djur.show(fm, "Dialog Jurusan 3");
                    }
                }
            }
        });
    }

    /**
        ======== IMPLEMENT REGISTER ON CLICK LISTENER
     */

    @Override
    public void onItemPilihSekolahClick(int position, List<ModelSekolahList> mList) {
//        makeToast(mList.get(position).getIdSekolah());
        binding.txtSekolah.setText(mList.get(position).getNamaSekolah());
        sIDSekolah = mList.get(position).getIdSekolah();
        this.mListSekolah = mList;

        // TODO : Setting Pilihan Jurusan Kosong
        binding.txtJurusan1.setText("");
        binding.txtJurusan2.setText("");
        binding.txtJurusan3.setText("");

    }

    @Override
    public void onItemPilihJurusan1(int position, List<ModelJurusanList> mList) {
        binding.txtJurusan1.setText(mList.get(position).getNamaJurusan());
        mListJurusan.clear();
        mListJurusan = mList;
        sPositionJur1 = position;
//        makeSnack(mList.get(position).getNamaJurusan());
        // TODO : Setting Pilihan Jurusan Kosong
        binding.txtJurusan2.setText("");
        binding.txtJurusan3.setText("");
    }

    @Override
    public void onItemPilihJurusan2(int position, List<ModelJurusanList> mList, boolean tidakmemilih) {
//        makeSnack(mList.get(position).getNamaJurusan());
       if (tidakmemilih)
       {
           binding.txtJurusan2.setText(getString(R.string.TidakMemilih));
           binding.txtJurusan3.setText(getString(R.string.TidakMemilih));
       } else {
           binding.txtJurusan2.setText(mList.get(position).getNamaJurusan());
           mListJurusan2.clear();
           mListJurusan2 = mList;
           sPositionJur2 = position;
           // TODO : Setting Pilihan Jurusan Kosong
           binding.txtJurusan3.setText("");
       }
    }

    @Override
    public void onItemPilihJurusan3(int position, List<ModelJurusanList> mList, boolean tidakmemilih) {
        if (tidakmemilih)
        {
            binding.txtJurusan3.setText(getString(R.string.TidakMemilih));
        } else {
            binding.txtJurusan3.setText(mList.get(position).getNamaJurusan());
        }
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

    private void makeToast(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void makeLogI(String msg)
    {
        Log.i("RegisterA ", msg);
    }
}