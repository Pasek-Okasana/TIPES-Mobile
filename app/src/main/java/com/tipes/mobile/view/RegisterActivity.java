package com.tipes.mobile.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.tipes.mobile.databinding.ActivityRegisterBinding;
import com.tipes.mobile.view.dialog.DialogSekolahFragment;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.txtSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DialogSekolahFragment dsekolah = DialogSekolahFragment.newInstance("haha");
                dsekolah.show(fm, "DialogSekolah");
            }
        });
    }
}