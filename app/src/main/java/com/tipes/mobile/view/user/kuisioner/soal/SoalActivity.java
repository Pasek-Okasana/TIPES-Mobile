package com.tipes.mobile.view.user.kuisioner.soal;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivitySoalBinding;

public class SoalActivity extends AppCompatActivity {
    private ActivitySoalBinding binding;
    private Bundle extras;
    private  int intIdKategori;
    private String sIdKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        settingToolbar();


        extras = getIntent().getExtras();

        sIdKategori = extras.getString(String.valueOf(R.string.idkategori));
        intIdKategori = Integer.parseInt(sIdKategori);

        makeSnack(sIdKategori);

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
//    private void makeLogI(String msg)
//    {
//        Log.i(TAG, msg);
//    }
//    private void makeLogD(String msg)
//    {
//        Log.d(TAG, msg);
//    }
}