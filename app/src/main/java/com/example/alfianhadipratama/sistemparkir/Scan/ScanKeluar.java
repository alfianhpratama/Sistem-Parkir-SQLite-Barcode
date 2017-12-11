package com.example.alfianhadipratama.sistemparkir.Scan;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.example.alfianhadipratama.sistemparkir.DataHelper;
import java.util.Calendar;
import java.util.Date;

public class ScanKeluar extends Activity {
    /** Called when the activity is first created. */
    private String upc;
    DataHelper dbHelper;
    EditText text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //jendela tanpa title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //memulai pemindaian QRCode
        IntentIntegrator.initiateScan(this);
    }
    // cek hasil dari QRCode
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        dbHelper = new DataHelper(this);
        switch(requestCode) {
            case IntentIntegrator.REQUEST_CODE: {
                if (resultCode != RESULT_CANCELED) {
                    IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                    // apabila ada hasil dari pemindaian
                    if (scanResult != null) {
                        // ambil isi dari QRCode
                        upc = scanResult.getContents();
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        Cursor a = db.rawQuery("SELECT * FROM waktu LEFT JOIN kendaraan ON waktu.plat_nomor = kendaraan.plat_nomor " +
                                "WHERE kendaraan.barcode='" + upc + "'", null);
                        a.moveToFirst();
                        if (a.getCount() > 0) {
                            Calendar cal = Calendar.getInstance();
                            DateFormat waktunya = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date det = new Date();
                            db.execSQL("INSERT INTO histori VALUES('" + a.getString(0).toString() + "', '"
                                    + a.getString(1).toString() + "', '" + a.getString(2).toString() + "', '"
                                    + waktunya.format(det) + "')");
                            db.execSQL("DELETE FROM waktu WHERE plat_nomor IN " +
                                    "(SELECT plat_nomor FROM kendaraan WHERE barcode='" + upc + "')");
                            MainScan.disini.RefreshList();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Data !", Toast.LENGTH_LONG).show();
                            MainScan.disini.RefreshList();
                            finish();
                        }
                    }
                    break;
                }
            }
        }
    }
}