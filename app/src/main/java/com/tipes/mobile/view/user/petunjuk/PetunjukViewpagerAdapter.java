package com.tipes.mobile.view.user.petunjuk;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tipes.mobile.view.user.petunjuk.fragment.BagianAFragment;
import com.tipes.mobile.view.user.petunjuk.fragment.BagianBFragment;
import com.tipes.mobile.view.user.petunjuk.fragment.BagianCFragment;
import com.tipes.mobile.view.user.petunjuk.fragment.BagianDFragment;

public class PetunjukViewpagerAdapter extends FragmentStateAdapter {
    public PetunjukViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0 :
                return new BagianAFragment();
            case 1 :
                return new BagianBFragment();
            case 2 :
                return new BagianCFragment();
            default :
                return new BagianDFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
