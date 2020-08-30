package com.tipes.mobile.Helper;

import com.tipes.mobile.model.soal.yesno.ModelSoalYNList;

import java.util.List;
import java.util.Locale;

public class UtilsData {
    public static String getJenisKelaminToNumberString(String jk)
    {
        String data , hasil = "" ;

        if (jk.equals("Laki-Laki"))
        {
            hasil = "1";

        } else if (jk.equals("Perempuan"))
        {
            hasil = "2";
        }
        return hasil;
    }

    public static String hitungTotalYA(List<ModelSoalYNList> mList) {
        String hasil;
        int count = 0;
        for (ModelSoalYNList row : mList)
        {
            if (row.getNumber() == 1)
            {
                count++;
            }

        }
        hasil = Integer.toString(count);
        return hasil;
    }

    public static String hitungTotaTidak(List<ModelSoalYNList> mList) {
        String hasil;
        int count = 0;
        for (ModelSoalYNList row : mList)
        {
            if (row.getNumber() == 2)
            {
                count++;
            }

        }
        hasil = Integer.toString(count);
        return hasil;
    }


    public static String updateCountTimeDownString(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        return timeLeftFormatted;
    }
}
