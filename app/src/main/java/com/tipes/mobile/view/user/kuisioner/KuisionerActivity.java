package com.tipes.mobile.view.user.kuisioner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.tipes.mobile.R;
import com.tipes.mobile.databinding.ActivityKuisionerBinding;
import com.tipes.mobile.model.kategory.ModelKategoriList;
import com.tipes.mobile.view.user.kuisioner.soal.yesno.SoalActivity;
import com.tipes.mobile.viewmodel.ViMoQuiz;

import java.util.ArrayList;
import java.util.List;

public class KuisionerActivity extends AppCompatActivity {
    private String TAG = KuisionerActivity.class.getSimpleName();
    private ActivityKuisionerBinding binding;
    private ViMoQuiz mViMoQuiz;

    private KuisionerAdapter mAdapter;
    private LinearLayoutManager mLayout;
    private List<ModelKategoriList> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKuisionerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        settingToolbar();
        mViMoQuiz = ViewModelProviders.of(this).get(ViMoQuiz.class);

        mAdapter = new KuisionerAdapter(mList);
        mLayout = new LinearLayoutManager(this);

        binding.recycleKuisioner.setHasFixedSize(true);
        binding.recycleKuisioner.setAdapter(mAdapter);
        binding.recycleKuisioner.setLayoutManager(mLayout);

        mViMoQuiz.getKategori().observe(this, data -> {
            if (data != null)
            {
                mList.clear();
                mList.addAll(data.getDataListst());
                mAdapter.notifyDataSetChanged();

            } else {
                makeToast(getString(R.string.MaafJaringanSibuk));
            }
        });

        whenAdapterClicked();
    }

    private void whenAdapterClicked() {
        mAdapter.setOnItemClickListener(new KuisionerAdapter.ClickListener() {
            @Override
            public void onCardClick(View v, int position) {
               int num = Integer.parseInt(mAdapter.getItemPosition(position).getIdKategori());
               if (num == 4)
               {
                   makeSnack("D");
               } else {
                   startActivity(
                           new Intent(KuisionerActivity.this, SoalActivity.class)
                                   .putExtra(
                                           String.valueOf(R.string.idkategori), mAdapter.getItemPosition(position).getIdKategori()
                                   )
                   );
               }
//                makeSnack(mAdapter.getItemPosition(position).getNamaKategori());
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