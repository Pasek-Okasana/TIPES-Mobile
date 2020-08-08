package com.tipes.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.databinding.ActivityLoginBinding;
import com.tipes.mobile.user.MainActivityUser;

public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // Status Bar Transparent
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );



        validasiform();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.inTxtUsername.getText().toString();
                String password = binding.inTxtPassword.getText().toString();

                if (validasiformkosong(username, password))
                {
                    makeLogI("Click Button");
                    makeSnack("berhasil bro");
                    startActivity(new Intent(LoginActivity.this, MainActivityUser.class));
//                    doLogin(username, password);
//                    makeLogD(HelperUtils.encryptPasswordSHA1(password));
                }
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