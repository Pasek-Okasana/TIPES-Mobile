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

import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivityRegisterBinding;
import com.tipes.mobile.model.sekolah.ModelSekolahList;
import com.tipes.mobile.view.dialog.DialogSekolahFragment;
import com.tipes.mobile.view.dialog.RegisterOnClickListener;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements RegisterOnClickListener {
    private ActivityRegisterBinding binding;
    private String sIDSekolah, sIDJur1, sIDJur2, sIDJur3;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getWindow().setStatusBarColor(R.color.colorGray);

        binding.txtSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DialogSekolahFragment dsekolah = DialogSekolahFragment.newInstance("haha");
                dsekolah.show(fm, "DialogSekolah");
            }
        });
    }

    @Override
    public void onItemPilihSekolahClick(int position, List<ModelSekolahList> mList) {
//        makeToast(mList.get(position).getIdSekolah());
        binding.txtSekolah.setText(mList.get(position).getNamaSekolah());
        sIDSekolah = mList.get(position).getIdSekolah();
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