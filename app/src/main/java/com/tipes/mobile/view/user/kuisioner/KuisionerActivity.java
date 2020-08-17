package com.tipes.mobile.view.user.kuisioner;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tipes.mobile.databinding.ActivityKuisionerBinding;

public class KuisionerActivity extends AppCompatActivity {
    private ActivityKuisionerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKuisionerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}