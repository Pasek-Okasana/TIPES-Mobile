package com.tipes.mobile.view.user.petunjuk;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivityPetunjukBinding;

public class PetunjukActivity extends AppCompatActivity {

    private ActivityPetunjukBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetunjukBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        settingToolbar();

        getWindow().setStatusBarColor(R.color.colorBlue74ebd5);
        /** Setting View pager */
        ViewPager2 viewPager2 = binding.viewpagerPetunjuk;
        viewPager2.setAdapter(new PetunjukViewpagerAdapter(this));
        /** Setting View Pager Dan Tab Sinkron */
        TabLayout tabLayout = binding.tabLayoutPetunjuk;
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position)
                {
                    case  0 :
                        {
                            tab.setText("Bagian A");
                            break;
                        }
                    case  1 :
                        {
                            tab.setText("Bagian B");
                            break;
                        }
                    case  2 :
                        {
                            tab.setText("Bagian C");
                            break;
                        }
                    case  3 :
                        {
                            tab.setText("Bagian D");
                            break;
                        }
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }


    private void settingToolbar() {
        Toolbar mToolbar = (Toolbar) binding.toolbarPetunjuk.getRoot();
        setSupportActionBar(mToolbar);
        binding.toolbarPetunjuk.txtToolbarName.setText(R.string.Petunjuk);

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