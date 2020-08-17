package com.tipes.mobile.Helper;

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
}
