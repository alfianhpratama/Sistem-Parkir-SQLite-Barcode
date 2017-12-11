package com.example.alfianhadipratama.sistemparkir.Scan;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.example.alfianhadipratama.sistemparkir.DataHelper;
import java.util.Calendar;
import static android.R.attr.password;

public class ScanMasuk extends Activity {
    /** Called when the activity is first created. */
    private String upc;
    DataHelper dbHelper;

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
                            Toast.makeText(getApplicationContext(), "Data has inputing !", Toast.LENGTH_LONG).show();
                            MainScan.disini.RefreshList();
                            finish();
                        } else {
                            Calendar cal = Calendar.getInstance();
                            DateFormat waktunya = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date det = new Date();
                            Cursor c = db.rawQuery("SELECT * FROM kendaraan WHERE barcode='" + upc + "'", null);
                            c.moveToFirst();
                            if (c.getCount() > 0) {
                                db.execSQL("INSERT INTO waktu VALUES('" + c.getString(1).toString() + "', '"
                                        + c.getString(0).toString() + "', '"
                                        + waktunya.format(det) + "')");
                                Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_LONG).show();
                                MainScan.disini.RefreshList();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Data didn't exist ! Please register to administrator", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}