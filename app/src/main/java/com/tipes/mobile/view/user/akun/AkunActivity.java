package com.tipes.mobile.view.user.akun;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.tipes.mobile.R;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.databinding.ActivityAkunBinding;
import com.tipes.mobile.model.akun.ModelAkunList;
import com.tipes.mobile.view.LoginActivity;
import com.tipes.mobile.viewmodel.ViMoUser;

public class AkunActivity extends AppCompatActivity {
    private ActivityAkunBinding binding;
    private ViMoUser mViMoUser;
    private SharedPrefManager mSPM;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAkunBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        settingToolbar();
        mViMoUser = ViewModelProviders.of(this).get(ViMoUser.class);
        mSPM = new SharedPrefManager(AkunActivity.this);
        getWindow().setStatusBarColor(R.color.colorBlue74ebd5);

        mViMoUser.getMyAkun().observe(AkunActivity.this, data -> {
            if (data != null)
            {
                ModelAkunList list = data.getAkunList().get(0);

                binding.txtAsalSekolah.setText(list.getAsalSekolah());
                binding.txtIdPendaftaran.setText(list.getUsername());
                binding.txtJurusan1.setText(list.getJurusan());
                binding.txtJurusan2.setText(list.getJurusan2());
                binding.txtJurusan3.setText(list.getJurusan3());
                binding.txtPekerjaandiinginkan.setText(list.getPekerjaan());
                binding.txtNamaLengkap.setText(list.getNamaLeng());

            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSPM.saveSPBoolean(String.valueOf(R.string.SP_STILL_LOGIN_APP), false);
                mSPM.saveSPString(String.valueOf(R.string.SP_USERNAME_APP), "");
                mSPM.saveSPInt(String.valueOf(R.string.SP_ROLE_APP), 0);
                startActivity(new Intent(AkunActivity.this, LoginActivity.class));
                finish();
            }
        });
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
}