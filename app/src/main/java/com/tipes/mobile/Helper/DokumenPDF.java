package com.tipes.mobile.Helper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.google.android.material.snackbar.Snackbar;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.tipes.mobile.model.hasilaksi.ModelHasilQuiz;
import com.tipes.mobile.model.hasilaksi.ModelHasilQuizData_Deskripsi;
import com.tipes.mobile.view.user.nilai.HasilActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DokumenPDF {
    private Locale localeID;
    public DokumenPDF() {
        localeID = new Locale("in","ID");
    }
    public void createHasilQuiz(ModelHasilQuiz dataHasil,
                                String sUsername, Context context, RelativeLayout root) {

        try {
            //path untuk menyimpan file dokument PDF
            String sWaktu, sFilename, sFilePath, sDirectory;
            sWaktu = new SimpleDateFormat("yyyyMMdd_HHmmss",
                    localeID).format(System.currentTimeMillis());
            sFilename = "TIPES-Mobile_" + sUsername + "_" + sWaktu + ".pdf";
            sDirectory = "/Download/TIPESDocument";

            //pdf file path
            sFilePath = Environment.getExternalStorageDirectory() + sDirectory ;
            File defaultFile = new File(sFilePath);

            if (!defaultFile.exists()) defaultFile.mkdirs();
            File file = new File(defaultFile,sFilename);
            if (file.exists()) {
                file.delete();
                file = new File(defaultFile,sFilename);
            }
            //buat objeck dokument
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
            document.setPageSize(PageSize.A4);
            document.addCreationDate();

            document.addAuthor("TIPES-MOBILE");
            document.addCreator("TIPES - MOBILE");

            //warna color Font
            BaseColor mColorAccent = new BaseColor(0,153,204,255);

            //ukururan Font
            float mHeadingFontSize = 20.0f;
            float mIsiFontSize = 20.0f;
            float mValueFontSize = 24.0f;

            //mendeklarasikan font
            BaseFont basecheckbox = BaseFont.createFont("res/font/wingdings.ttf",BaseFont.IDENTITY_H,false);
            BaseFont baseuncheckbox = BaseFont.createFont("res/font/wingdings1.ttf",BaseFont.IDENTITY_H,false);
            BaseFont montserratregular = BaseFont.createFont("res/font/montserratregular.ttf","UTF-8",BaseFont.EMBEDDED);


            //garis pemisah (garis horisaontal)
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineColor(new BaseColor(0,0,0,68));

            Font fontheader = new Font(montserratregular,36.0f,Font.NORMAL,BaseColor.BLACK);
            Font fontsubnormal = new Font(montserratregular,mValueFontSize,Font.NORMAL,BaseColor.BLACK);
            Font fontisinormal = new Font(montserratregular,mIsiFontSize,Font.NORMAL,BaseColor.BLACK);
            Font fontnormalcolorblue = new Font(montserratregular,mValueFontSize,Font.NORMAL,mColorAccent);

            //Chunk mempresentasikan potongan dari sebuah text, Chunk dapat berisi singel karater atau bebrapa kalimat

            //Nama paket
            Chunk namapaketchunk = new Chunk("HASIL QUISIONER - TIPES Mobile", fontheader);
            Paragraph namapaketparagraf = new Paragraph(namapaketchunk);
            namapaketparagraf.setAlignment(Element.ALIGN_LEFT);
            document.add(namapaketparagraf);

            //username
            Chunk nousername = new Chunk("Username : " + sUsername, fontnormalcolorblue);
            Paragraph usernameparagraf = new Paragraph(nousername);
            usernameparagraf.setAlignment(Element.ALIGN_LEFT);
            document.add(usernameparagraf);

            //lineSeparator
            document.add(new Paragraph(""));
            document.add(new Chunk(lineSeparator));
            document.add(new Paragraph(""));

            //KETERANGAN HASIL QUIZ
            Chunk hasilquizchunk = new Chunk("Hasil Quiz", fontsubnormal);
            Paragraph hasilquizparagraf = new Paragraph(hasilquizchunk);
            hasilquizparagraf.setAlignment(Element.ALIGN_CENTER);
            document.add(hasilquizparagraf);

            //lineSeparator
            document.add(new Paragraph(""));
            document.add(new Chunk(lineSeparator));
            document.add(new Paragraph(""));

            //Kompentensi
            Chunk kompentensichunk = new Chunk(new VerticalPositionMark());
            Paragraph kompentensiparagraf = new Paragraph("Kompentensi",fontisinormal);
            kompentensiparagraf.add(new Chunk(kompentensichunk));
            kompentensiparagraf.add(dataHasil.getData().getKompentensi());
            document.add(kompentensiparagraf);

            //Lulus
            Chunk luluschunk = new Chunk(new VerticalPositionMark());
            Paragraph lulusparagraf = new Paragraph("Lulus",fontisinormal);
            lulusparagraf.add(new Chunk(luluschunk));
            lulusparagraf.add(dataHasil.getData().getLulus());
            document.add(lulusparagraf);

            //Keterangan
            Chunk keteranganchunk = new Chunk(new VerticalPositionMark());
            Paragraph keteranganparagraf = new Paragraph("Keterangan",fontisinormal);
            keteranganparagraf.add(new Chunk(keteranganchunk));
            keteranganparagraf.add(dataHasil.getData().getKeterangan());
            document.add(keteranganparagraf);

            //lineSeparator
            document.add(new Paragraph(""));
            document.add(new Chunk(lineSeparator));
            document.add(new Paragraph(""));

            //KETERANGAN DESKRIPSI
            Chunk deskripsiquizchunk = new Chunk("Deskripsi Hasil Quiz", fontsubnormal);
            Paragraph deskripsiquizparagraf = new Paragraph(deskripsiquizchunk);
            deskripsiquizparagraf.setAlignment(Element.ALIGN_CENTER);
            document.add(deskripsiquizparagraf);

//            TODO : MULAI DENGAN FOR

            for (ModelHasilQuizData_Deskripsi dataDeskripsi : dataHasil.getData().getListDeskripsi()){
                //lineSeparator
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //TIPE
                Chunk tipechunk = new Chunk(new VerticalPositionMark());
                Paragraph tipeparagraf = new Paragraph("Tipe ",fontisinormal);
                tipeparagraf.add(new Chunk(tipechunk));
                tipeparagraf.add(dataDeskripsi.getTipe());
                document.add(tipeparagraf);

//            Keterangan Kepribadian
                Chunk kepribadianchunk = new Chunk("Kepribadian", fontisinormal);
                Paragraph kepribadianparagraf = new Paragraph(kepribadianchunk);
                kepribadianparagraf.setAlignment(Element.ALIGN_CENTER);
                document.add(kepribadianparagraf);

//            ISI Keterangan Kepribadian
                Chunk isikepribadianchunk = new Chunk(dataDeskripsi.getKepribadian(), fontisinormal);
                Paragraph isikepribadianparagraf = new Paragraph(isikepribadianchunk);
                document.add(isikepribadianparagraf);
            }

            document.close();

            Snackbar snackbar1 = Snackbar
                    .make(root, "Data Tersimpan di /Download/TIPESDocument", Snackbar.LENGTH_LONG)
                    .setDuration(6000);

            snackbar1.show();
            snackbar1.setAction("Lihat", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar1.dismiss();
                    viewPdf(context, sFilename , sDirectory);
                }
            });
        } catch (IOException | DocumentException ie) {
            Log.e("createPdf IO",ie.getLocalizedMessage());
        } catch (ActivityNotFoundException ae) {
            Log.e("createPdf AC",ae.getLocalizedMessage());
        }
    }


    // Method for opening a pdf file
    private void viewPdf(Context context, String file, String directory) {

        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + directory + "/" + file);

        Uri uri;

        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);

        pdfIntent = new Intent(Intent.ACTION_VIEW);
        //Log.e("pathOpen", file.getPath());
        uri = Uri.fromFile(pdfFile);
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= 24) {

            Uri apkURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", pdfFile);
            pdfIntent.setDataAndType(apkURI, "application/pdf");
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            pdfIntent.setDataAndType(uri, "application/pdf");
        }


        try {
            context.startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }

    public void savePdfHasilQuiz(ModelHasilQuiz dataHasil,
                                  String sUsername, Context context) {
        //create object of Document class
        Document mDoc = new Document();
        //pdf file name
        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        //pdf file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/Download/" + mFileName + ".pdf";

        try {
            //create instance of PdfWriter class
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            //open the document for writing
            mDoc.open();
            //get text from EditText i.e. mTextEt
            String mText = "mTextEt.getText().toString()";

            //add author of the document (optional)
            mDoc.addAuthor("Nyoba");

            //add paragraph to the document
            mDoc.add(new Paragraph(mText));

            //close the document
            mDoc.close();
            //show message that file is saved, it will show file name and file path too
            Toast.makeText(context, mFileName +".pdf\nis saved to\n"+ mFilePath, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            //if any thing goes wrong causing exception, get and show exception message
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("createPdf AC",e.getMessage());
        }
    }

}
