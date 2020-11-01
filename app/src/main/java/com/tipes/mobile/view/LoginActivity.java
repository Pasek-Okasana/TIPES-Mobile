package com.tipes.mobile.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.databinding.ActivityLoginBinding;
import com.tipes.mobile.view.user.MainActivityUser;
import com.tipes.mobile.viewmodel.ViMoUser;

public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private SharedPrefManager mSPM;
    private int role;

    private ViMoUser mVMUser;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // Status Bar Transparent

        getWindow().setStatusBarColor(R.color.colorGray);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );

        binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
        mVMUser = ViewModelProviders.of(this).get(ViMoUser.class);

        mSPM = new SharedPrefManager(LoginActivity.this);

        // Cek Login
        if (mSPM.getSPRole() > 0)
        {
            role = mSPM.getSPRole();
        }

        // pengecekan apkah sudah login apa belum
        if (mSPM.getSPStillLogin())
        {
            if (role == 2)
            {
                startActivity(
                        new Intent(this, MainActivityUser.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                );

            } else if (role == 1)
            {
//                startActivity(
//                        new Intent(this, BottomNavigationMedisActivity.class)
//                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
//                );

            }
            this.finish();
        }

        validasiform();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.inTxtUsername.getText().toString();
                String password = binding.inTxtPassword.getText().toString();

                if (validasiformkosong(username, password))
                {
                    makeLogI("Click Button");
                    doLogin(username, password);
//                    makeLogD(HelperUtils.encryptPasswordSHA1(password));
                }
            }
        });

        binding.txtRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void doLogin(String username, String password) {

        binding.loadingCustom.fragLoading.setVisibility(View.VISIBLE);
        mVMUser.loginUser(username, password).observe(this, data -> {
            binding.loadingCustom.fragLoading.setVisibility(View.INVISIBLE);
            if (data != null)
            {
                int role= 2;

//                if (data.getLevel().equals("admin"))
//                {
//                    role = 1;
//                } else if (data.getLevel().equals("mahasiswa"))
//                {
//                    role = 2;
//                }
                mSPM.saveSPBoolean(String.valueOf(R.string.SP_STATUS_A), true);
                mSPM.saveSPBoolean(String.valueOf(R.string.SP_STATUS_B), true);
                mSPM.saveSPBoolean(String.valueOf(R.string.SP_STATUS_C), true);
                mSPM.saveSPBoolean(String.valueOf(R.string.SP_STATUS_D), true);
                mSPM.saveSPBoolean(String.valueOf(R.string.SP_STILL_LOGIN_APP), true);
                mSPM.saveSPString(String.valueOf(R.string.SP_USERNAME_APP), username);
//                mSPM.saveSPString(String.valueOf(R.string.SP_ID_USER_APP), "12");
//                mSPM.saveSPString(String.valueOf(R.string.SP_PASSWORD_APP), "enc");
                mSPM.saveSPInt(String.valueOf(R.string.SP_ROLE_APP), role);

                if (role == 2 )
                {
                    startActivity(
                            new Intent(this, MainActivityUser.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("username", "data.getNama_p()")
                    );
                    finish();
                } else if (role == 1)
                {
//            startActivity(
//                    new Intent(this, BottomNavigationActivity.class)
//                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
//                            .putExtra("username", data.getNama_p())
//            );
                    makeToast("Admin");
//                    finish();
                } // if role
            } else
            {
                mSPM.saveSPBoolean(String.valueOf(R.string.SP_STILL_LOGIN_APP), false);
                mSPM.saveSPInt(String.valueOf(R.string.SP_ROLE_APP), 0);
                makeToast("Maaf... Akun Tidak Ditemukan !");
            }
        });


    }

    /**
     ==================== Validasi Form
     */
    private void validasiform()
    {
        binding.inTxtUsername.addTextChangedListener(cekUsername);
        binding.inTxtPassword.addTextChangedListener(cekPassword);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean validasiformkosong(String user, String password)
    {
        for (int i =0 ; i < 3; i++)
        {
            switch (i)
            {
                case 0 :
                    if (user == null || user.trim().length() == 0)
                    {
                        // code block to be executed
                        binding.inTxtUsername.setBackground(
                                getResources()
                                        .getDrawable(
                                                R.drawable.radius_outline_border_red_10dp)
                        );
                        binding.inTxtUsername.setError(getResources().getString(R.string.KolomKosong));

                    };
                    break;
                case 1 :
                    if (password == null || password.trim().length() == 0)
                    {
                        // code block to be executed
                        binding.inTxtPassword.setBackground(
                                getResources()
                                        .getDrawable(
                                                R.drawable.radius_outline_border_red_10dp)
                        );
                        binding.inTxtPassword.setError(getResources().getString(R.string.KolomKosong));
                    };
                    break;
                case 2 :
                    if (user == null || user.trim().length() == 0 && password == null || password.trim().length() == 0)
                    {
                        return false;
                    }
            }

        }
        return true;
    }
    private final TextWatcher cekUsername = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0)
            {
                // code block to be executed
                binding.inTxtUsername.setBackground(
                        getResources()
                                .getDrawable(
                                        R.drawable.radius_outline_border_red_10dp)
                );
                binding.inTxtUsername.setError(getResources().getString(R.string.KolomKosong));
                binding.btnLogin.setEnabled(false);

            } else {
                // code block to be executed
                binding.inTxtUsername.setBackground(
                        getResources()
                                .getDrawable(
                                        R.drawable.radius_outline_border_green_10dp)
                );
                binding.btnLogin.setEnabled(true);
            }
        }
    };

    private final TextWatcher cekPassword = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() < 1)
            {
                // code block to be executed
                binding.inTxtPassword.setError(getResources().getString(R.string.KolomKosong));
                binding.btnLogin.setEnabled(false);
            } else
            {
                // code block to be executed
                binding.inTxtPassword.setBackground(
                        getResources()
                                .getDrawable(
                                        R.drawable.radius_outline_border_green_10dp)
                );
                binding.btnLogin.setEnabled(true);
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0)
            {
                // code block to be executed
                binding.inTxtPassword.setBackground(
                        getResources()
                                .getDrawable(
                                        R.drawable.radius_outline_border_red_10dp)
                );
                binding.inTxtPassword.setError(getResources().getString(R.string.KolomKosong));
            }

        }
    };

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