package com.tipes.mobile.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivityRegisterBinding;
import com.tipes.mobile.model.ModelJenisKelamin;
import com.tipes.mobile.model.sekolah.ModelJurusanList;
import com.tipes.mobile.model.sekolah.ModelSekolahList;
import com.tipes.mobile.view.dialog.DialogJenisKelaminFragment;
import com.tipes.mobile.view.dialog.DialogJurusanFragment;
import com.tipes.mobile.view.dialog.DialogSekolahFragment;
import com.tipes.mobile.view.dialog.RegisterOnClickListener;
import com.tipes.mobile.viewmodel.ViMoUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements RegisterOnClickListener {
    private ActivityRegisterBinding binding;
    private String sIDSekolah, sIDJurusan1, sIDJurusan2, sIDJurusan3, sIDJk;
    private int sPositionJur1, sPositionJur2;
    private List<ModelJurusanList> mListJurusan = new ArrayList<>();
    private List<ModelJurusanList> mListJurusan2 = new ArrayList<>();
    private ViMoUser mViModelUser;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mViModelUser = ViewModelProviders.of(this).get(ViMoUser.class);
        binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);

        getWindow().setStatusBarColor(R.color.colorGray);
        validasiKolomAuto();
        onClickButtonJurusan();

    }

    private void onClickButtonJurusan() {

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

        binding.txtJenisKelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DialogJenisKelaminFragment djk = new DialogJenisKelaminFragment();
                djk.show(fm, "Dialog Jenis Kelamin");
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validasiKolomKosong() && validasiMatchPassword()){
//                    makeSnack("Berhasil Bro");

                    doRegister();
                }
            }
        });
    }

    private void doRegister() {
        String leghtUsr, leghtNamLeng, leghtPkerja, leghtSklh, leghtJur1, leghtJur2, leghtJur3, leghtPass, leghtPassKom;
        leghtUsr = binding.inTxtUsername.getText().toString();
        leghtNamLeng = binding.inTxtNamaLengkap.getText().toString();
        leghtPkerja = binding.inTxtPekerjaan.getText().toString();
        leghtPass = binding.inTxtPassword.getText().toString();
        leghtPassKom = binding.inTxtPasswordKonfirm.getText().toString();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("username", leghtUsr);
        parameter.put("password", leghtPass);
        parameter.put("level","mahasiswa" );
        parameter.put("jenis_kelamin", sIDJk);
        parameter.put("nama_lengkap", leghtNamLeng);
        parameter.put("jurusan", sIDJurusan1);
        parameter.put("jurusan2", sIDJurusan2 );
        parameter.put("jurusan3", sIDJurusan3 );
        parameter.put("asal_sekolah", sIDSekolah);
        parameter.put("pekerjaan", leghtPkerja);

        binding.loadingCustom.fragLoading.setVisibility(View.VISIBLE);
        mViModelUser.registerUser(parameter).observe(this, data -> {
            binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
            if (data != null)
            {
                makeToast("Berhasil Mendaftar...");
                this.finish();
            } else {
                makeSnack("Gagal Melakukan Pendaftaran... Silahkan Cek Kembali Data Yang Dimasukkan !");
            }
        });
    }


    /**
     ======== VALIDASI DATA ===========
     */

    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean validasiKolomKosong() {
        int leghtUsr, leghtNamLeng, leghtPkerja, leghtSklh, leghtJur1, leghtJur2, leghtJur3, leghtPass, leghtPassKom;
        leghtUsr = binding.inTxtUsername.length();
        leghtNamLeng = binding.inTxtNamaLengkap.length();
        leghtPkerja = binding.inTxtPekerjaan.length();
        leghtSklh = binding.txtSekolah.length();
        leghtJur1 = binding.txtJurusan1.length();
        leghtJur2 = binding.txtJurusan2.length();
        leghtJur3 = binding.txtJurusan3.length();
        leghtPass = binding.inTxtPassword.length();
        leghtPassKom = binding.inTxtPasswordKonfirm.length();

        if (leghtUsr < 1){
            binding.inTxtUsername.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.inTxtUsername.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtNamLeng < 1)
        {
            binding.inTxtNamaLengkap.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.inTxtNamaLengkap.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtPkerja < 1){
            binding.inTxtPekerjaan.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.inTxtPekerjaan.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtSklh < 1){
            binding.txtSekolah.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.txtSekolah.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtJur1 < 1){
            binding.txtJurusan1.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.txtJurusan1.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtJur2 < 1){
            binding.txtJurusan2.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.txtJurusan2.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtJur3 < 1){
            binding.txtJurusan3.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.txtJurusan3.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtPass < 1){
            binding.inTxtPassword.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.inTxtPassword.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtPassKom < 1){
            binding.inTxtPasswordKonfirm.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
        } else {
            binding.inTxtPasswordKonfirm.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
        }

        if (leghtUsr < 1 || leghtNamLeng < 1 || leghtPkerja < 1 || leghtSklh < 1 || leghtJur1 < 1 || leghtJur2 < 1 || leghtJur3 < 1 || leghtPass < 1 || leghtPassKom < 1 )
        {
            return false;
        } else return true;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean validasiMatchPassword() {
        String psswd, psswdkonfirm;
        psswd = binding.inTxtPassword.getText().toString();
        psswdkonfirm = binding.inTxtPasswordKonfirm.getText().toString();

        if (!psswdkonfirm.equals(psswd)){
            binding.inTxtPasswordKonfirm.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_red_10dp
                            )
            );
//            makeSnack(getResources().getString(R.string.PasswordTidakCocok));
            binding.inTxtPasswordKonfirm.setError(getResources().getString(R.string.PasswordTidakCocok));
            return false;
        } else {
            binding.inTxtPasswordKonfirm.setBackground(
                    getResources()
                            .getDrawable(
                                    R.drawable.radius_outline_border_gray_10dp
                            )
            );
            return  true;
        }
    }

    private void validasiKolomAuto()
    {
        binding.inTxtPasswordKonfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 5)
                {
                    binding.inTxtPasswordKonfirm.setBackground(
                            getResources()
                                    .getDrawable(
                                            R.drawable.radius_outline_border_gray_10dp
                                    )
                    );
                } else {
                    binding.inTxtPasswordKonfirm.setBackground(
                            getResources()
                                    .getDrawable(
                                            R.drawable.radius_outline_border_red_10dp
                                    )
                    );
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.inTxtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 5)
                {
                    binding.inTxtPassword.setBackground(
                            getResources()
                                    .getDrawable(
                                            R.drawable.radius_outline_border_gray_10dp
                                    )
                    );
                } else {
                    binding.inTxtPassword.setBackground(
                            getResources()
                                    .getDrawable(
                                            R.drawable.radius_outline_border_red_10dp
                                    )
                    );
                    binding.inTxtPassword.setError("Password Minimal 6 Karakter !");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
        ======== IMPLEMENT REGISTER ON CLICK LISTENER ===========
     */

    @Override
    public void onItemPilihSekolahClick(int position, List<ModelSekolahList> mList) {
//        makeToast(mList.get(position).getIdSekolah());
        binding.txtSekolah.setText(mList.get(position).getNamaSekolah());
        sIDSekolah = mList.get(position).getIdSekolah();
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
        sIDJurusan1 = mList.get(position).getIdJurusan();
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
           sIDJurusan2 = "0";
           sIDJurusan3 = "0";
           binding.txtJurusan2.setText(getString(R.string.TidakMemilih));
           binding.txtJurusan3.setText(getString(R.string.TidakMemilih));
       } else {
           sIDJurusan2 = mList.get(position).getIdJurusan();
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
            sIDJurusan3 = "0";
            binding.txtJurusan3.setText(getString(R.string.TidakMemilih));
        } else {
            sIDJurusan3 = mList.get(position).getIdJurusan();
            binding.txtJurusan3.setText(mList.get(position).getNamaJurusan());
        }
    }

    @Override
    public void onItemPilihJK(int position, List<ModelJenisKelamin> mList) {
        sIDJk = mList.get(position).getIdJk();
        binding.txtJenisKelamin.setText(mList.get(position).getJk());
    }


    //-===============================================================================

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