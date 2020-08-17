package com.tipes.mobile.view.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivityMainUserBinding;
import com.tipes.mobile.model.MenuDashboardModel;
import com.tipes.mobile.view.user.akun.AkunActivity;
import com.tipes.mobile.view.user.petunjuk.PetunjukActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        loadDisplayHari();
        tambahDataMenu();
        setMenu();
        onClickItemMenu();

        // Setting Status Bar Color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
        }

        binding.txtUsername.setText("Nama Orang");
    }

    private void onClickItemMenu() {
        mAdapter.setOnItemClickListener(new MainUserAdapter.ClickListener() {
            @Override
            public void onCardClick(View v, int position) {
                makeLogI("Berhasil Diclik " + position);
                switch (position)
                {
                    case 0 :
                        startActivity(new Intent(MainActivityUser.this, PetunjukActivity.class));
                        break;
                    case 1 :
                        makeSnack("Kuisioner");
                        break;
                    case 2 :
                        makeSnack("Nilai");
                        break;
                    case 3 :
                        startActivity(new Intent(MainActivityUser.this, AkunActivity.class));
                        break;
                }
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

    private void loadDisplayHari() {

        Locale INDO = new Locale( "in" , "ID" );
        Calendar cal_item = Calendar.getInstance();
        String hari_ini = DateFormat.getDateInstance(DateFormat.FULL, INDO).format(cal_item.getTime());
        int timeOfDay = cal_item.get(Calendar.HOUR_OF_DAY);

        binding.txtTanggal.setText(hari_ini);

        if (timeOfDay <= 5 || timeOfDay > 23 && timeOfDay <= 5){
            binding.txtGreeting.setText("Selamat Pagi");
        } else if (timeOfDay > 5 && timeOfDay < 11) {
            binding.txtGreeting.setText("Selamat Pagi");
        } else if (timeOfDay >= 11 && timeOfDay <= 15) {
            binding.txtGreeting.setText("Selamat Siang");
        }  else if (timeOfDay > 16 && timeOfDay <= 18) {
            binding.txtGreeting.setText("Selamat Sore");
        } else if (timeOfDay > 18 && timeOfDay <= 21) {
            binding.txtGreeting.setText("Selamat Malam");
        } else if (timeOfDay > 21 && timeOfDay <= 23) {
            binding.txtGreeting.setText("Selamat Malam");
        }

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