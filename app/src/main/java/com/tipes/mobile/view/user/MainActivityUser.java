package com.tipes.mobile.view.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivityMainUserBinding;
import com.tipes.mobile.model.MenuDashboardModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityUser extends AppCompatActivity {
    private String TAG = MainActivityUser.class.getSimpleName();

    private ActivityMainUserBinding binding;
    private List<MenuDashboardModel> mMenuList, mList;

    private GridLayoutManager mLayoutManager;
    private MainUserAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tambahDataMenu();
        setMenu();
        onClickItemMenu();

        binding.txtGreeting.setText("Selamat Pagi");
        binding.txtUsername.setText("Nama Orang");
        binding.txtTanggal.setText("1 agustus 2020");
    }

    private void onClickItemMenu() {
        mAdapter.setOnItemClickListener(new MainUserAdapter.ClickListener() {
            @Override
            public void onCardClick(View v, int position) {
                makeLogI("Berhasil Diclik " + position);
            }
        });
    }

    private void setMenu() {
        mList = new ArrayList<>();
        mAdapter = new MainUserAdapter(mMenuList);
        mLayoutManager = new GridLayoutManager(MainActivityUser.this, 1);
        binding.recyclerHomeUser.setHasFixedSize(true);

        binding.recyclerHomeUser.setLayoutManager(mLayoutManager);
        binding.recyclerHomeUser.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    private void tambahDataMenu() {
        mMenuList = new ArrayList<>();
        mMenuList.add(new MenuDashboardModel("Petunjuk", "Klik Item Untuk Melihat Petunjuk Pengisian Kuisioner", R.drawable.ic_folder_e_32dp));
        mMenuList.add(new MenuDashboardModel("Kuisioner", "Klik Item Untuk Melakukan Pengisian Kuisioner", R.drawable.ic_pencil_e_32dp));
        mMenuList.add(new MenuDashboardModel("Nilai", "Klik IKtem Untuk Melihat Hasil", R.drawable.ic_centang_green_32dp));
        mMenuList.add(new MenuDashboardModel("Akun", "Klik Item Untuk Melihat Informasi Akun & Logout", R.drawable.ic_user_e_32dp));
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
    private void makeLogI(String msg)
    {
        Log.i(TAG, msg);
    }
    private void makeLogD(String msg)
    {
        Log.d(TAG, msg);
    }
}